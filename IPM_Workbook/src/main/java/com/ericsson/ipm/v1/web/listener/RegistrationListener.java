package com.ericsson.ipm.v1.web.listener;

import java.util.UUID;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.ericsson.ipm.v1.domain.UserProfile;
import com.ericsson.ipm.v1.service.UserProfileService;
import com.ericsson.v1.util.AdaptersConfiguration;
import com.ericsson.v1.util.Constants;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationListener.class);
	
	private static final Configuration CONFIG = AdaptersConfiguration
			.getInstance().getConfiguration();
	
    private UserProfileService userProfileService;

    private MessageSource messageSource;

    private JavaMailSender mailSender;

    private Environment env;

    // API

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        UserProfile user = event.getUser();
        String token = UUID.randomUUID().toString();
        userProfileService.createVerificationTokenForUser(user, token);

        final SimpleMailMessage email = constructEmailMessage(event, user, token);
        Object isEnabled = CONFIG.getProperty("ipm.mail.is_enabled");
		if(isEnabled != null && "true".equalsIgnoreCase(isEnabled.toString())){
			 mailSender.send(email);
		}
    }

    //

    private final SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final UserProfile user, final String token) {
        final String recipientAddress = user.getEmailId();
        final String subject = "Registration Confirmation Entranship Student";
        final String confirmationUrl = event.getAppUrl() + Constants.BASE_PUBLIC_URL_PATH+"regitrationConfirm.html?token=" + token;
        LOGGER.info("confirmationUrl : "+confirmationUrl);
        final String message = messageSource.getMessage("message.regSucc", null, event.getLocale());
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

    @Autowired
	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

    @Autowired
	public void setMessages(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

    @Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

    @Autowired
	public void setEnv(Environment env) {
		this.env = env;
	}
    
    
    

}
