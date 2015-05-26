package com.ericsson.ipm.v1.web.controllers;

import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ericsson.ipm.v1.domain.UserProfile;
import com.ericsson.ipm.v1.domain.VerificationToken;
import com.ericsson.ipm.v1.dto.RegistrationDTO;
import com.ericsson.ipm.v1.exception.EmailExistsException;
import com.ericsson.ipm.v1.service.RoleService;
import com.ericsson.ipm.v1.service.UserProfileService;
import com.ericsson.ipm.v1.validator.UserValidator;
import com.ericsson.ipm.v1.web.listener.OnRegistrationCompleteEvent;
import com.ericsson.v1.util.AdaptersConfiguration;
import com.ericsson.v1.util.Constants;



@Controller
@RequestMapping(Constants.BASE_PUBLIC_URL_PATH)
public class RegistrationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	
	
	private static final Configuration CONFIG = AdaptersConfiguration
			.getInstance().getConfiguration();

	private UserProfileService userProfileService;
	
	
    private ApplicationEventPublisher eventPublisher;
    
    private UserValidator userValidator;
    
    private MessageSource messages;
    
    private Environment env;
    
    private JavaMailSender mailSender;
    
    private RoleService roleService;
    
    
    /*@InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }*/
	
	@RequestMapping(value =Constants.IPM_CONTROLLER_REGISTRATIONCONTROLLER_SHOWREGISTRATIONFORM_URI + Constants.IPM_CONTROLLER_URL_SUFFIX, method = RequestMethod.GET)
    public String showRegistrationForm(final HttpServletRequest request, final Model model) {
        LOGGER.debug("Rendering registration page.");
        final RegistrationDTO registrationDTO = new RegistrationDTO();
        model.addAttribute("registrationDTO", registrationDTO);
        model.addAttribute("roles", roleService.findAll()); 
        return "public/registration";
    }
	
	// final Errors errors, 
	@RequestMapping(value = Constants.IPM_CONTROLLER_REGISTRATIONCONTROLLER_REGISTERUSERACCOUNT_URI + Constants.IPM_CONTROLLER_URL_SUFFIX, method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("registrationDTO") @Valid final RegistrationDTO registrationDTO, 
    		final BindingResult result, final HttpServletRequest request, Model model) {
        LOGGER.debug("Registering user account with information: {}", registrationDTO);
        userValidator.validate(registrationDTO, result);
        if (result.hasErrors()) {
        	model.addAttribute("roles", roleService.findAll()); 
            return new ModelAndView("public/registration", "registrationDTO", registrationDTO);
        }

        final UserProfile registered = createUserAccount(registrationDTO);
        if (registered == null) {
            result.rejectValue("emailId", "message.regError");
            return new ModelAndView("public/registration", "user", registrationDTO);
        }
        try {
            final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
        } catch (final Exception ex) {
            LOGGER.warn("Unable to register user", ex);
            return new ModelAndView("emailError", "registrationDTO", registrationDTO);
        }
        return new ModelAndView("public/successRegister", "registrationDTO", registrationDTO);
    }
	// http://localhost:8080/ipm/v1/public/regitrationConfirm.html?token=17e0c639-0086-409a-8d9b-00fdf1950db5
	@RequestMapping(value = Constants.IPM_CONTROLLER_REGISTRATIONCONTROLLER_REGITRATIONCONFIRM_URI + Constants.IPM_CONTROLLER_URL_SUFFIX, method = RequestMethod.GET)
    public String confirmRegistration(final Locale locale, final Model model, @RequestParam("token") final String token) {
        final VerificationToken verificationToken = userProfileService.getVerificationToken(token);
        
        final Calendar cal = Calendar.getInstance();
        if (verificationToken != null) {
        	LOGGER.info("verificationToken.getExpiryDate().getTime() : "+verificationToken.getExpiryDate().getTime());
            LOGGER.info("cal.getTime().getTime() : "+cal.getTime().getTime());
            LOGGER.info("(verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) : "+(verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()));
            
        }
        
        if (verificationToken == null) {
            final String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "public/invalidUser";
        }

        final UserProfile user = verificationToken.getUser();
        
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            model.addAttribute("message", messages.getMessage("auth.message.expired", null, locale));
            model.addAttribute("expired", true);
            model.addAttribute("token", token);
            return "public/invalidUser";
        }

        user.setIsEnabled(Boolean.TRUE);
        userProfileService.update(user);
        model.addAttribute("message", messages.getMessage("message.accountVerified", null, locale));
        return "public/login";
    }

    // user activation - verification

    @RequestMapping(value = Constants.IPM_CONTROLLER_REGISTRATIONCONTROLLER_RESENDREGISTRATIONTOKEN_URI + Constants.IPM_CONTROLLER_URL_SUFFIX, method = RequestMethod.GET)
    public String resendRegistrationToken(final HttpServletRequest request, final Model model, 
    		@RequestParam("token") final String existingToken) {
    	final Locale locale = request.getLocale();
        final VerificationToken newToken = userProfileService.generateNewVerificationToken(existingToken);
        final UserProfile user = userProfileService.getUser(newToken.getToken());
        final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        try {
        final SimpleMailMessage email = constructResetVerificationTokenEmail(appUrl, request.getLocale(), newToken, user);
        
        Object isEnabled = CONFIG.getProperty("ipm.mail.is_enabled");
		if(isEnabled != null && "true".equalsIgnoreCase(isEnabled.toString())){
			 mailSender.send(email);
		}
       
        
        } catch (final MailAuthenticationException e) {
            LOGGER.debug("MailAuthenticationException", e);
            return "public/emailError";
        } catch (final Exception e) {
            LOGGER.debug(e.getLocalizedMessage(), e);
            model.addAttribute("message", e.getLocalizedMessage());
            return "public/login";
        }
        model.addAttribute("message", messages.getMessage("message.resendToken", null, locale));
        return "public/login";
    }
	
	
	private UserProfile createUserAccount(final RegistrationDTO userDTO) {
        UserProfile registered = null;
        try {
            registered = userProfileService.registerNewUserAccount(userDTO);
        } catch (final EmailExistsException e) {
            return null;
        }
        return registered;
    }
	
	// NON-API

	private final SimpleMailMessage constructResetVerificationTokenEmail(final String contextPath, final Locale locale, 
			final VerificationToken newToken, final UserProfile user) {
        final String confirmationUrl = contextPath + Constants.BASE_PUBLIC_URL_PATH+"regitrationConfirm.html?token=" + newToken.getToken();
        LOGGER.info("constructResetVerificationTokenEmail confirmationUrl : "+confirmationUrl);
        final String message = messages.getMessage("message.resendToken", null, locale);
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Resend Registration Token : Entranship Student");
        email.setText(message + " \r\n" + confirmationUrl);
        email.setTo(user.getEmailId());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

	@Autowired
	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}


	@Autowired
	public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	@Autowired
	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}

	@Autowired
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}

	@Autowired
	public void setEnv(Environment env) {
		this.env = env;
	}

	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	
	
	/*protected void compareCaptcha(final RegistrationDTO registrationDTO, final BindingResult result, final HttpSession session, final HttpServletRequest request) throws Exception {
		Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
		request.setCharacterEncoding(
				Constants.UTF_CHAR_ENC);
		// fix for idle session-timeout nulling out captcha
		if (captcha == null || !captcha.isCorrect(registrationDTO.getCaptchaCode())) {
			//addActionError(getText(INVALID_CAPTCHA_CODE_MSG_KEY));
		}
	}
	*/

}
