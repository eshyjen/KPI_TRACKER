package com.ericsson.ipm.v1.web.controllers;

import java.io.PrintWriter;
import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ericsson.ipm.v1.domain.Asset;
import com.ericsson.ipm.v1.domain.DeliveryQuality;
import com.ericsson.ipm.v1.domain.UserProfile;
import com.ericsson.ipm.v1.security.authentication.vo.ContextAuthenticatedUserDetailsVO;
import com.ericsson.ipm.v1.service.AssetService;
import com.ericsson.ipm.v1.service.DeliveryQualityService;
import com.ericsson.ipm.v1.service.UserProfileService;
import com.ericsson.v1.util.Constants;

/**
 * @author iqbal.hosain.khan@ericsson.com
 *
 */

@Controller
@RequestMapping(Constants.BASE_PROTECTED_URL_PATH)
public class DeliveryQualityController extends BaseController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeliveryQualityController.class);

	private UserProfileService userProfileService;

	private DeliveryQualityService  deliveryQualityService;

	@RequestMapping(value = "deliveryQualityDetails.html", method = RequestMethod.GET)
	public String getDeliveryQualityDetails(Model model, Principal prinicpal,
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("prinicpal : " + prinicpal);
		UserProfile userProfile = null;
		ContextAuthenticatedUserDetailsVO loggedInUser = getCurrentUser();
		LOGGER.info("loggedInUser : " + loggedInUser);
		if (loggedInUser != null) {
			UserProfile profile = loggedInUser.getProfile();
			LOGGER.info("profile : " + profile);
			LOGGER.info("profile.getId() : " + profile.getId());
			userProfile = userProfileService.findByIdWithDeliveryQuality(profile.getId());
		}

		if (prinicpal != null) {
			String useName = prinicpal.getName();
			LOGGER.info("useName : " + useName);
		}

		LOGGER.info("userProfile : " + userProfile);
		if (userProfile != null) {
			LOGGER.info("userProfile getDeliveryQualities : " + userProfile.getDeliveryQualities());
		}
		LOGGER.info("useName : " + userProfile);
		model.addAttribute(Constants.DELIVERY_QUALITIY_LIST, userProfile.getDeliveryQualities());
		return "protected/deliveryQuality";
	}

	@RequestMapping(value = "saveDeliveryQualityDetails.html", method = RequestMethod.POST)
	public String saveDeliveryQualityDetails(Model model, Principal prinicpal,
			HttpServletRequest request, HttpServletResponse response) {

		boolean flag = false;
		String resultMessage = " ";
		
		String deliveryQualityId = request.getParameter("deliveryQualityId");
		String projectType = request.getParameter("projectType");
		String dqiScore = request.getParameter("dqiScore");
		String dpiScore = request.getParameter("dpiScore");
		String dqiDate = request.getParameter("dqiDate");
		String pmOrSpmName = request.getParameter("pmOrSpmName");
		String tlName = request.getParameter("tlName");
		String swArchitectName = request.getParameter("swArchitectName");

		LOGGER.debug("deliveryQualityId : " + deliveryQualityId);
		LOGGER.debug("projectType : " + projectType);
		LOGGER.debug("dqiScore : " + dqiScore);
		LOGGER.debug("dpiScore : " + dpiScore);
		LOGGER.debug("dqiDate : " + dqiDate);
		LOGGER.debug("pmOrSpmName : " + pmOrSpmName);
		LOGGER.debug("tlName : " + tlName);
		LOGGER.debug("swArchitectName : " + swArchitectName);

		LOGGER.debug("getQueryString : " + request.getQueryString());
		LOGGER.debug("prinicpal : " + prinicpal);
		UserProfile userProfile = null;
		ContextAuthenticatedUserDetailsVO loggedInUser = getCurrentUser();
		LOGGER.debug("loggedInUser : " + loggedInUser);
		if (loggedInUser != null) {
			UserProfile profile = loggedInUser.getProfile();
			LOGGER.debug("profile : " + profile);
			userProfile = userProfileService.getRefById(profile.getId());
		}

		if (prinicpal != null) {
			String useName = prinicpal.getName();
			LOGGER.debug("useName : " + useName);
		}

		DeliveryQuality deliveryQuality = null;
		if (StringUtils.isNotBlank(deliveryQualityId)) {
			Integer deliveryQualit_id = Integer.parseInt(deliveryQualityId);
			deliveryQuality = deliveryQualityService.findById(deliveryQualit_id);
			resultMessage = "<font color='green'>DQI Updated Successfully</font>";
		} else {
			flag = true;
			deliveryQuality = new DeliveryQuality();
			deliveryQuality.setUserprofile(userProfile);
			resultMessage = "<font color='green'>DQI Created Successfully</font>";
		}

		
		deliveryQuality.setDpiScore(dpiScore);
		deliveryQuality.setDqiDate(new Date());
		deliveryQuality.setDqiScore(dqiScore);
		deliveryQuality.setPmOrSpmName(pmOrSpmName);
		deliveryQuality.setProjectType(projectType);
		deliveryQuality.setSwArchitectName(swArchitectName);
		deliveryQuality.setTlName(tlName);
		
		try {
			if (flag)
				deliveryQualityService.save(deliveryQuality);
			else
				deliveryQualityService.update(deliveryQuality);
		} catch (Exception e) {
			resultMessage = "<font color='red'>Sorry!! DQI cannot be Created / Updated.</font>";
			LOGGER.debug("exception : " + e.getStackTrace());
		}

		/*try {
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			PrintWriter out = response.getWriter();
			out.print(resultMessage);
			out.close();
		} catch (Exception e) {
			resultMessage = "<font color='red'>Sorry!! DQI cannot be Created / Updated.</font>";
			LOGGER.debug("exception : " + e.getStackTrace());
		}*/
		
		userProfile = userProfileService.findByIdWithDeliveryQuality(userProfile.getId());
		model.addAttribute(Constants.DELIVERY_QUALITIY_LIST, userProfile.getDeliveryQualities());

		return "protected/deliveryQuality";
	}

	@RequestMapping(value = "deliveryQualityDetail.html", method = RequestMethod.GET)
	public void getDeliveryQualityDetail(Model model, Principal prinicpal,
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("prinicpal : " + prinicpal);
		UserProfile userProfile = null;
		StringBuffer buffer = new StringBuffer();
		ContextAuthenticatedUserDetailsVO loggedInUser = getCurrentUser();
		LOGGER.debug("loggedInUser : " + loggedInUser);
		if (loggedInUser != null) {
			UserProfile profile = loggedInUser.getProfile();
			LOGGER.debug("profile : " + profile);
			// userProfile =
			// userProfileService.findByIdWithAsset(profile.getId());
		}

		if (prinicpal != null) {
			String useName = prinicpal.getName();
			LOGGER.debug("useName : " + useName);
		}

		String deliveryQualityId = request.getParameter("deliveryQualityId");
		Integer deliveryQuality_id = Integer.parseInt(deliveryQualityId);
		DeliveryQuality entity = deliveryQualityService.findById(deliveryQuality_id);
	
		
		buffer.append("deliveryQualityId");
		buffer.append("***");
		buffer.append(entity.getId());
		buffer.append("###");

		buffer.append("projectType");
		buffer.append("***");
		buffer.append(entity.getProjectType());
		buffer.append("###");

		buffer.append("dqiScore");
		buffer.append("***");
		buffer.append(entity.getDqiScore());
		buffer.append("###");

		buffer.append("dpiScore");
		buffer.append("***");
		buffer.append(entity.getDpiScore());
		buffer.append("###");

		buffer.append("dqiDate");
		buffer.append("***");
		buffer.append(entity.getDqiDate());
		buffer.append("###");
		
		buffer.append("pmOrSpmName");
		buffer.append("***");
		buffer.append(entity.getPmOrSpmName());
		buffer.append("###");
		
		buffer.append("tlName");
		buffer.append("***");
		buffer.append(entity.getTlName());
		buffer.append("###");
		
		buffer.append("swArchitectName");
		buffer.append("***");
		buffer.append(entity.getSwArchitectName());
		buffer.append("###");

		try {
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			PrintWriter out = response.getWriter();
			out.print(buffer.toString());
			out.close();
		} catch (Exception e) {
			LOGGER.debug("getDeliveryQualityDetail exception : " + e.getStackTrace());
		}

		// model.addAttribute(Constants.ASSET_LIST, userProfile.getAssets());
		return; // "protected/asset";
	}

	@RequestMapping(value = "removeDeliveryQualityDetail.html", method = RequestMethod.GET)
	public void removeDeliveryQualityDetail(Model model, Principal prinicpal,
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("removeAssetDetail : ");
		LOGGER.debug("prinicpal : " + prinicpal);
		UserProfile userProfile = null;
		ContextAuthenticatedUserDetailsVO loggedInUser = getCurrentUser();
		LOGGER.debug("loggedInUser : " + loggedInUser);
		if (loggedInUser != null) {
			UserProfile profile = loggedInUser.getProfile();
			LOGGER.debug("profile : " + profile);
			// userProfile =
			// userProfileService.findByIdWithAsset(profile.getId());
		}

		if (prinicpal != null) {
			String useName = prinicpal.getName();
			LOGGER.debug("useName : " + useName);
		}

		String assetId = request.getParameter("id");

		LOGGER.debug("assetId : " + assetId);

		// model.addAttribute(Constants.ASSET_LIST, userProfile.getAssets());
		return; // "protected/asset";
	}

	public UserProfileService getUserProfileService() {
		return userProfileService;
	}

	@Autowired
	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	
	public DeliveryQualityService getDeliveryQualityService() {
		return deliveryQualityService;
	}

	@Autowired
	public void setDeliveryQualityService(
			DeliveryQualityService deliveryQualityService) {
		this.deliveryQualityService = deliveryQualityService;
	}
	
	

}
