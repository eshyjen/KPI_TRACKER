/**
 * 
 */
package com.ericsson.ipm.v1.web.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ericsson.ipm.v1.domain.KPI;
import com.ericsson.ipm.v1.domain.KPIRoleAssignment;
import com.ericsson.ipm.v1.domain.Role;
import com.ericsson.ipm.v1.domain.UserProfile;
import com.ericsson.ipm.v1.domain.UserRoleAssignment;
import com.ericsson.ipm.v1.security.authentication.vo.ContextAuthenticatedUserDetailsVO;
import com.ericsson.ipm.v1.service.UserProfileService;
import com.ericsson.v1.util.Constants;

/**
 * @author iqbal.hosain.khan@ericsson.com
 *
 */
@Controller
@RequestMapping(Constants.BASE_PROTECTED_URL_PATH)
public class UserController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	
	private UserProfileService userProfileService;
	
	@RequestMapping(value="userDetails.html", method=RequestMethod.GET)
	public String getUserDetails(Model model) {
		ContextAuthenticatedUserDetailsVO loggedInUser = getCurrentUser();
		LOGGER.debug("loggedInUser : "+loggedInUser);
		model.addAttribute(loggedInUser.getProfile());
		return "protected/userdetails";
	}
	
	@RequestMapping(value="ipmDashboard.html", method=RequestMethod.GET)
	public String getIPMDashboard(Model model, Principal prinicpal) {
		List<KPI> kpis = new ArrayList<KPI>();
		LOGGER.debug("prinicpal : "+prinicpal);
		
		ContextAuthenticatedUserDetailsVO loggedInUser = getCurrentUser();
		LOGGER.debug("loggedInUser : "+loggedInUser);
		if(loggedInUser != null){
			UserProfile profile = loggedInUser.getProfile();
			LOGGER.debug("profile : "+profile);
			
			for(UserRoleAssignment  userRoleAssignment : profile.getRoleAssignments()){
				Role role = userRoleAssignment.getRole();
				for(KPIRoleAssignment KPIRoleAssignment : role.getKpiRoleAssignments()) {
					KPI kpi = KPIRoleAssignment.getKpi();
					kpis.add(kpi);					
				}
				
			}
		}
		
		if(prinicpal != null){
			String useName = prinicpal.getName();
			LOGGER.debug("useName : "+useName);
		}
		
		
		model.addAttribute("kpis", kpis);
		return "protected/welcome";
	}
	
	
	
	

	@Autowired
	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*private static UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		UserController.userService = userService;
	}
	
	public static User getCurrentUser()
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    if (principal instanceof UserDetails) 
	    {
	    	String email = ((UserDetails) principal).getUsername();
	    	User loginUser = userService.findUserByEmail(email);
	    	return new SecurityUser(loginUser);
	    }

	    return null;
	}*/
}

