/**
 * 
 */
package com.ericsson.ipm.v1.web.controllers;

import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
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
import com.ericsson.ipm.v1.dto.KPIIdNameDTO;
import com.ericsson.ipm.v1.security.authentication.vo.ContextAuthenticatedUserDetailsVO;
import com.ericsson.ipm.v1.service.UserProfileService;
import com.ericsson.v1.util.Constants;
import com.ericsson.v1.util.IPMUtility;


/**
 * @author iqbal.hosain.khan@ericsson.com
 * <%-- <jsp:include page="/WEB-INF/views/public/include_header.jsp" /> --%>
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
	
	/*<%
	
	Set<KPIIdNameDTO> kpiIdNameDTOs = (HashSet<KPIIdNameDTO>)session.getAttribute("kpiDisplayNames");
	for(KPIIdNameDTO dto : kpiIdNameDTOs){
		
	}
	%>*/
	
	@RequestMapping(value="ipmDashboard.html", method=RequestMethod.GET)
	public String getIPMDashboard(Model model, Principal prinicpal, HttpServletRequest request) {
		List<KPI> kpis = new ArrayList<KPI>();
		Set<KPIIdNameDTO> kpiIdNameDTOs = new HashSet<KPIIdNameDTO>();
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
					KPIIdNameDTO nameDTO = new KPIIdNameDTO();
					nameDTO.setId(IPMUtility.getParamValue(kpi.getKpiDisplayName()));
					nameDTO.setName(kpi.getKpiDisplayName());
					kpiIdNameDTOs.add(nameDTO);
				}
				
			}
		}
		
		if(prinicpal != null){
			String useName = prinicpal.getName();
			LOGGER.debug("useName : "+useName);
		}
		
		
		model.addAttribute("kpis", kpis);
		HttpSession session = request.getSession();
		session.setAttribute("kpiDisplayNames", kpiIdNameDTOs);
		//model.addAttribute("kpiDisplayNames", kpiIdNameDTOs);
		return "protected/welcome";
	}
	
	@Autowired
	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}
	
	
	
	
	@RequestMapping(value="updateUserDetails.html", method=RequestMethod.POST)
	public String updateUserDetails(HttpServletRequest request, Model model) {
		String[] props = {
		"userFristName",
		"userLastName",
		"emailId",
		"costCenter",
		"currentLineManager",
		"educationalQualification",
		"employeeId",
		"jobRole",
		"jobStage",
		"lastYearIPMRating",
		"manHourRate",
		"previousLineManeger",
		"previousOrganisation",
		"signunId",
		"yearOfIPM",
		"yearOfLastPromotion"};
		
		// "dateOfJoinInMediaAccount",
		// "id",
		// "totalEricssonExperienceInMonths",
		// "totalITExperience",
		// "totalYearsOfExperience",
		
		LOGGER.debug("updateUserDetails : "+request);
		
		/*ContextAuthenticatedUserDetailsVO loggedInUser = getCurrentUser();
		LOGGER.debug("loggedInUser : "+loggedInUser);
			UserProfile profile = loggedInUser.getProfile();
			LOGGER.debug("profile : "+profile);*/
			
			List<UserProfile> userProfiles = userProfileService.findBySignumId(request.getParameter("signunId"));
			UserProfile profile = null;
			if (userProfiles != null && userProfiles.size() > 0) {
				profile = userProfiles.get(0);
			}
			for (int i = 0; i < props.length; i++) {
			String value = request.getParameter(props[i]);
			try {
				BeanUtils.copyProperty(profile, props[i], value);
			} catch (IllegalAccessException e) {
				LOGGER.error("updateUserDetails IllegalAccessException : "+profile);
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				LOGGER.error("updateUserDetails InvocationTargetException : "+profile);
				e.printStackTrace();
			}
		}
		
			profile = userProfileService.update(profile);
			model.addAttribute(profile);
			LOGGER.debug("updateUserDetails profile : "+profile);
		return "protected/userdetails";
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

