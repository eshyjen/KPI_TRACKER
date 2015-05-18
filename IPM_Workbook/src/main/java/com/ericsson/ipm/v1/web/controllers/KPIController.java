package com.ericsson.ipm.v1.web.controllers;

import java.security.Principal;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ericsson.ipm.v1.domain.KPIRoleAssignment;
import com.ericsson.ipm.v1.dto.KPIDTO;
import com.ericsson.ipm.v1.dto.KPIRoleDTO;
import com.ericsson.ipm.v1.service.KPIService;
import com.ericsson.ipm.v1.service.RoleService;
import com.ericsson.ipm.v1.service.UserProfileService;
import com.ericsson.ipm.v1.validator.KPIValidator;
import com.ericsson.v1.util.Constants;
import com.ericsson.v1.util.IPMUtility;

@Controller
@RequestMapping(Constants.BASE_PROTECTED_URL_PATH)
@SessionAttributes("kpiDTO")
public class KPIController extends BaseController {

	// https://learn.jquery.com/using-jquery-core/faq/how-do-i-check-uncheck-a-checkbox-input-or-radio-button/
	
	// http://www.tutorialrepublic.com/faq/how-to-check-a-checkbox-is-checked-or-not-using-jquery.php
	// https://learn.jquery.com/using-jquery-core/faq/how-do-i-get-the-text-value-of-a-selected-option/
	
	// http://api.jquery.com/val/
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KPIController.class);

	private UserProfileService userProfileService;
	
	private KPIService kpiService;
	
	private RoleService roleService;
	
	private KPIValidator kpiValidator;
	
	
	/*@RequestMapping(value="kpiDetails.html", method=RequestMethod.GET)
	public String getKPIDetails(Model model, Principal prinicpal, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("prinicpal : "+prinicpal);
		UserProfile userProfile = null;
		ContextAuthenticatedUserDetailsVO loggedInUser = getCurrentUser();
		LOGGER.info("loggedInUser : "+loggedInUser);
		if(loggedInUser != null){
			UserProfile profile = loggedInUser.getProfile();
			LOGGER.info("profile : "+profile);
			LOGGER.info("profile.getId() : "+profile.getId());
			userProfile = userProfileService.findByIdWithAsset(profile.getId());
		}
		
		if(prinicpal != null){
			String useName = prinicpal.getName();
			LOGGER.info("useName : "+useName);
		}
		
		LOGGER.info("userProfile : "+userProfile);
		if(userProfile != null ){
			LOGGER.info("userProfile getAssets : "+userProfile.getAssets());
		}
		LOGGER.info("useName : "+userProfile);
		model.addAttribute(Constants.ASSET_LIST, userProfile.getAssets());
		return "protected/asset";
	}*/
	
	
	@RequestMapping(value="kpisDetails.html", method=RequestMethod.GET)
	public String getKPIsDetail(Model model, Principal prinicpal, HttpServletRequest request, HttpServletResponse response) {
		
		List<KPIRoleDTO> kpiRoleDTOs = kpiService.getKPIRoleDetails();
		model.addAttribute(Constants.KPI_LIST1, kpiRoleDTOs);
		return "protected/kpi";
		
	}
	
	
	
	//http://outbottle.com/spring-3-mvc-adding-objects-to-a-list-element-on-the-fly-at-form-submit-generic-method/
	
	
	@RequestMapping(value="/addKPI.html",method = RequestMethod.GET)
    public String setupForm(Model model, HttpServletRequest request, HttpServletResponse response) 
	{
		//Map referenceData = new HashMap();
		//Map<String,String> roles = new LinkedHashMap<String,String>();
		 KPIDTO kpiDTO = new KPIDTO();
		 //kpiDTO.setRoles(roleService.findAll());
		 
		 /*Role role1 = new Role();
		 role1.setId(2);
		 
		 Role role2 = new Role();
		 role2.setId(4);
		 */
		 //kpiDTO.getUserSelectedRoles().add(2);
		 //kpiDTO.getUserSelectedRoles().add(5);
		 //kpiDTO.getKpiRoleDTO().setKpiValueSWD("Iqbal Hosin");
		 model.addAttribute("kpiDTO", kpiDTO); 
		 model.addAttribute("roles", roleService.findAll()); 
		 //referenceData.put("javaSkillsList", javaSkill);
		 return "protected/addKPI";
    }
	
	@RequestMapping(value="addKPIDetails.html", method=RequestMethod.POST)
	public String saveKPIDetails(@ModelAttribute("kpiDTO") KPIDTO kpiDTO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) {
		
		LOGGER.info("kpiDTO : "+kpiDTO);
		if(kpiDTO != null){
			LOGGER.info("kpiDTO getId : "+kpiDTO.getId());
			LOGGER.info("kpiDTO getKpiDescription : "+kpiDTO.getKpiDescription());
			LOGGER.info("kpiDTO getKpiDisplayName : "+kpiDTO.getKpiDisplayName());
			LOGGER.info("kpiDTO getKpiName : "+kpiDTO.getKpiName());
			LOGGER.info("kpiDTO getKpiValue : "+kpiDTO.getKpiValue());
			LOGGER.info("kpiDTO getUserSelectedRoles : "+kpiDTO.getUserSelectedRoles());
			
			kpiValidator.validate(kpiDTO, result);
			
			if (result.hasErrors()) {
				 model.addAttribute("roles", roleService.findAll()); 
				return "protected/addKPI";
			}
			
			Enumeration<String> params = request.getParameterNames();
			while(params.hasMoreElements()){
				String param = (String) params.nextElement();
				LOGGER.info("param : "+param);
				}
			
			List<KPIRoleAssignment> kpiRoleAssignments = IPMUtility.populateKPIRoleFromUI(kpiDTO, roleService, request);
			
			for(KPIRoleAssignment kpiRoleAssignment : kpiRoleAssignments){
				LOGGER.info("kpiRoleAssignment : "+kpiRoleAssignment);
				LOGGER.info("kpiRoleAssignment getKpi : "+kpiRoleAssignment.getKpi());
				LOGGER.info("kpiRoleAssignment getRole : "+kpiRoleAssignment.getRole().getId());
			}
		}
		
		List<KPIRoleDTO> kpiRoleDTOs = kpiService.getKPIRoleDetails();
		model.addAttribute(Constants.KPI_LIST1, kpiRoleDTOs);
		//model.addAttribute(Constants.KPI_LIST1, new ArrayList<KPIRoleDTO>());
		
		//Mark Session Complete
		 status.setComplete();
		//return "redirect:getAll.html";
		
		return "protected/kpi";
		
	}
	
	
	@RequestMapping(value="kpiDetails.html", method=RequestMethod.GET)
	public String getKPIDetails(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		
			
			/*List<KPIRoleAssignment> kpiRoleAssignments = IPMUtility.populateKPIRoleFromUI(kpiDTO, roleService, request);
			
			for(KPIRoleAssignment kpiRoleAssignment : kpiRoleAssignments){
				LOGGER.info("kpiRoleAssignment : "+kpiRoleAssignment);
				LOGGER.info("kpiRoleAssignment getKpi : "+kpiRoleAssignment.getKpi());
				LOGGER.info("kpiRoleAssignment getRole : "+kpiRoleAssignment.getRole().getId());
			}*/
		
		String id = request.getParameter("id");
		
		KPIDTO kpiDTO = kpiService.getKPIDetails(id);
				
		LOGGER.info("kpiDTO : "+kpiDTO);
		
		if(kpiDTO != null){
			LOGGER.info("kpiDTO getId : "+kpiDTO.getId());
			LOGGER.info("kpiDTO getKpiDescription : "+kpiDTO.getKpiDescription());
			LOGGER.info("kpiDTO getKpiDisplayName : "+kpiDTO.getKpiDisplayName());
			LOGGER.info("kpiDTO getKpiName : "+kpiDTO.getKpiName());
			LOGGER.info("kpiDTO getKpiValue : "+kpiDTO.getKpiValue());
			LOGGER.info("kpiDTO getUserSelectedRoles : "+kpiDTO.getUserSelectedRoles());
		}
		
		//List<KPIRoleDTO> kpiRoleDTOs = kpiService.getKPIRoleDetails();
		//model.addAttribute(Constants.KPI_LIST1, kpiRoleDTOs);
		 //model.addAttribute(Constants.KPI_LIST1, new ArrayList<KPIRoleDTO>());
		 model.addAttribute("kpiDTO", kpiDTO); 
		 model.addAttribute("roles", roleService.findAll()); 
		
		return "protected/addKPI";
		
	}
	
	
	/*@RequestMapping(value="addKPIDetails.html", method=RequestMethod.GET)
	public String saveKPIDetails(@ModelAttribute("kpiDTO") KPIDTO kpiDTO, HttpServletRequest request, HttpServletResponse response) {
		return null;
		
	}*/
	

	@Autowired
	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	@Autowired
	public void setKpiService(KPIService kpiService) {
		this.kpiService = kpiService;
	}


	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Autowired
	public void setValidator(KPIValidator kpiValidator) {
		this.kpiValidator = kpiValidator;
	}	
	
	
	
}
