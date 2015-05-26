package com.ericsson.ipm.v1.web.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ericsson.ipm.v1.domain.OperationalDiscipline;
import com.ericsson.ipm.v1.domain.UserProfile;
import com.ericsson.ipm.v1.dto.OperationalDisciplineDTO;
import com.ericsson.ipm.v1.security.authentication.vo.ContextAuthenticatedUserDetailsVO;
import com.ericsson.ipm.v1.service.OperationalDisciplineService;
import com.ericsson.ipm.v1.service.UserProfileService;
import com.ericsson.v1.util.Constants;


@Controller
@RequestMapping(Constants.BASE_PROTECTED_URL_PATH)
public class OperationalDisciplineController extends BaseController {

private static final Logger LOGGER = LoggerFactory.getLogger(OperationalDisciplineController.class);

private OperationalDisciplineService operationalDisciplineService;

private UserProfileService userProfileService;

@RequestMapping(value ="operationalDiscipline.html", method = RequestMethod.GET)
public String showOperationalDisciplineForm(final HttpServletRequest request, final Model model) {
    LOGGER.debug("Rendering operationalDiscipline.jsp page.");
    final OperationalDisciplineDTO operationalDisciplineDTO = new OperationalDisciplineDTO();
    model.addAttribute("operationalDisciplineDTO", operationalDisciplineDTO);
    //model.addAttribute("roles", roleService.findAll());
    return "protected/operationalDiscipline";
}

	@RequestMapping(value="saveUpdateOperationalDiscipline.html", method=RequestMethod.POST)
	public String updateOperationalDiscipline(@ModelAttribute("operationalDisciplineDTO")OperationalDisciplineDTO operationalDisciplineDTO, HttpServletRequest request, Model model) {
		String[] props = {
				"operationalDisciplineName",
				"frequency",
				"quarter",
				"nonCompliance"};

				LOGGER.debug("updateOperationalDiscipline frequency from request: "+request.getParameter("frequency"));
				LOGGER.debug("updateOperationalDiscipline Name: "+operationalDisciplineDTO.getOperationalDisciplineName());
				LOGGER.debug("updateOperationalDiscipline Frequency: "+operationalDisciplineDTO.getFrequency());
				LOGGER.debug("updateOperationalDiscipline NonCompliance: "+operationalDisciplineDTO.getNonCompliance());
				LOGGER.debug("updateOperationalDiscipline Quarter: "+operationalDisciplineDTO.getQuarter());

				operationalDisciplineService.save(operationalDisciplineDTO);
				return "protected/operationalDiscipline";
	}


	@RequestMapping(value="operationalDisciplineList.html", method=RequestMethod.GET)
	public String getOperationalDisciplineDetails(Model model, Principal prinicpal, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("prinicpal : "+prinicpal);
		UserProfile  userProfile = null;
		ContextAuthenticatedUserDetailsVO loggedInUser = getCurrentUser();
		LOGGER.info("loggedInUser : "+loggedInUser);
		if(loggedInUser != null){
			UserProfile profile = loggedInUser.getProfile();
			LOGGER.info("profile : "+profile);
			LOGGER.info("profile.getId() : "+profile.getId());
			userProfile = userProfileService.findByIdWithOperationalDiscipline(profile.getId());
		}

		if(prinicpal != null){
			String useName = prinicpal.getName();
			LOGGER.info("useName : "+useName);
		}

		LOGGER.info("userProfile : "+userProfile);
		if(userProfile != null ){
			LOGGER.info("userProfile getAssets : "+userProfile.getOperationaldiscplines());
		}
		LOGGER.info("useName : "+userProfile);
		model.addAttribute(Constants.OPERATIONAL_DISCIPLINE_LIST, userProfile.getOperationaldiscplines());
		return "protected/operationalDiscipline";
	}

	@RequestMapping(value="operationalDisciplineForUpdate.html", method=RequestMethod.GET)
	public String getOperationalDisciplineForUpdate(Model model, HttpServletRequest request, HttpServletResponse response) {

		LOGGER.info("Operational Discipline ID:"+request.getParameter("id"));
		String id = request.getParameter("id");
		OperationalDiscipline operationalDiscipline = operationalDisciplineService.getOperationalDisciplineDetail(request.getParameter("id"));
		if(operationalDiscipline!=null){
			final OperationalDisciplineDTO operationalDisciplineDTO = new OperationalDisciplineDTO();
			operationalDisciplineDTO.setId(id);
			operationalDisciplineDTO.setOperationalDisciplineName(operationalDiscipline.getOperationalDisciplineName());
			operationalDisciplineDTO.setFrequency(operationalDiscipline.getFrequency());
			operationalDisciplineDTO.setQuarter(operationalDiscipline.getQuarter());
			operationalDisciplineDTO.setNonCompliance(operationalDiscipline.getNonCompliance());
			model.addAttribute("operationalDisciplineDTO", operationalDisciplineDTO);
		}
		return "protected/operationalDiscipline_Update";
	}

	/*@RequestMapping(value="removeAssetDetail.html", method=RequestMethod.POST)
	public String removeAssetDetail(Model model, Principal prinicpal, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("removeAssetDetail : ");
		LOGGER.debug("prinicpal : "+prinicpal);
		UserProfile userProfile = null;
		UserProfile profile = null;
		ContextAuthenticatedUserDetailsVO loggedInUser = getCurrentUser();
		LOGGER.debug("loggedInUser : "+loggedInUser);
		if(loggedInUser != null){
			profile = loggedInUser.getProfile();
			LOGGER.debug("profile : "+profile);
			//userProfile = userProfileService.findByIdWithAsset(profile.getId());
		}

		if(prinicpal != null){
			String useName = prinicpal.getName();
			LOGGER.debug("useName : "+useName);
		}

		String assetId = request.getParameter("id");

		LOGGER.debug("assetId : "+assetId);


		assetService.remove(assetId);
		userProfile = userProfileService.findByIdWithAsset(profile.getId());
		model.addAttribute(Constants.ASSET_LIST, userProfile.getAssets());

		return "protected/asset";
	}
*/


	/**
	 * @param operationalDisciplineService the operationalDisciplineService to set
	 */
	@Autowired
	public void setOperationalDisciplineService(
			OperationalDisciplineService operationalDisciplineService) {
		this.operationalDisciplineService = operationalDisciplineService;
	}

	/**
	 * @param userProfileService the userProfileService to set
	 */
	@Autowired
	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}



}
