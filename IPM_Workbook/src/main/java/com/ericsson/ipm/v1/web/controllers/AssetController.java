package com.ericsson.ipm.v1.web.controllers;

import java.io.PrintWriter;
import java.security.Principal;
import java.text.SimpleDateFormat;
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
import com.ericsson.ipm.v1.domain.UserProfile;
import com.ericsson.ipm.v1.security.authentication.vo.ContextAuthenticatedUserDetailsVO;
import com.ericsson.ipm.v1.service.AssetService;
import com.ericsson.ipm.v1.service.UserProfileService;
import com.ericsson.v1.util.Constants;

@Controller
@RequestMapping(Constants.BASE_PROTECTED_URL_PATH)
public class AssetController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AssetController.class);

	private UserProfileService userProfileService;
	
	private AssetService assetService;
	
	@RequestMapping(value="assetDetails.html", method=RequestMethod.GET)
	public String getAssetDetails(Model model, Principal prinicpal, HttpServletRequest request, HttpServletResponse response) {
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
	}
	
	@RequestMapping(value="saveAssetDetails.html", method=RequestMethod.POST)
	public String saveAssetDetails(Model model, Principal prinicpal, HttpServletRequest request, HttpServletResponse response) {
		
		boolean flag = false;
		String resultMessage=" ";
		String assetId                    =request.getParameter("assetId");
		String assetName                  =request.getParameter("assetName");
		String assetShortDescription      =request.getParameter("assetShortDescription");
		String projectName                =request.getParameter("projectName");
		String registeredInAssetPortal    =request.getParameter("registeredInAssetPortal");
		String reusedInOtherProjectsName  =request.getParameter("reusedInOtherProjectsName");
		String creationDate  =request.getParameter("creationDate");
		String effortSave=request.getParameter("effortSave");
		
		LOGGER.debug("assetId : "+assetId);
		LOGGER.debug("assetName : "+assetName);
		LOGGER.debug("assetShortDescription : "+assetShortDescription);
		LOGGER.debug("projectName : "+projectName);
		LOGGER.debug("registeredInAssetPortal : "+registeredInAssetPortal);
		LOGGER.debug("reusedInOtherProjectsName : "+reusedInOtherProjectsName);
		LOGGER.debug("effortSave : "+effortSave);
		LOGGER.debug("creationDate : "+creationDate);
		String pattern = "MM/dd/yyyy";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
		
		LOGGER.debug("getQueryString : "+request.getQueryString());
		LOGGER.debug("prinicpal : "+prinicpal);
		UserProfile userProfile = null;
		ContextAuthenticatedUserDetailsVO loggedInUser = getCurrentUser();
		LOGGER.debug("loggedInUser : "+loggedInUser);
		UserProfile profile = null;
		if(loggedInUser != null){
			profile = loggedInUser.getProfile();
			LOGGER.debug("profile : "+profile);
			userProfile = userProfileService.getRefById(profile.getId());
		}
		
		if(prinicpal != null){
			String useName = prinicpal.getName();
			LOGGER.debug("useName : "+useName);
		}
		
		Asset asset = null;
		 if(StringUtils.isNotBlank(assetId)){
				Integer asset_id=Integer.parseInt(assetId);
				asset =assetService.findById(asset_id);
				resultMessage="<font color='green'>Asset Updated Successfully</font>";
			}else{
				flag = true;
				asset =new Asset();
				asset.setUserprofile(userProfile);
				resultMessage="<font color='green'>Asset Created Successfully</font>";
			}
	
		try {
			 Date dateOfAssetCreation = format.parse(creationDate);
			 asset.setApprovalStatus("APPROVED");
			 asset.setAssetName(assetName);
			 asset.setAssetShortDescription(assetShortDescription);
			 asset.setCreationDate(dateOfAssetCreation);
			 asset.setEffortSave(effortSave);
			 asset.setModifiedDate(new Date());
			 asset.setProjectName(projectName);
			 asset.setRegisteredInAssetPortal(registeredInAssetPortal);
			 asset.setReusedInOtherProjectsName(reusedInOtherProjectsName);
			if (flag)
				assetService.save(asset);
			else
				assetService.update(asset);
		} catch (Exception e) {
			resultMessage = "<font color='red'>Sorry!! Product cannot be Created / Updated.</font>";
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
			resultMessage = "<font color='red'>Sorry!! Product cannot be Created / Updated.</font>";
			LOGGER.debug("exception : " + e.getStackTrace());
		}*/
		
		userProfile = userProfileService.findByIdWithAsset(profile.getId());
		model.addAttribute(Constants.ASSET_LIST, userProfile.getAssets());

		return "protected/asset";
	}
	
	
	@RequestMapping(value="assetDetail.html", method=RequestMethod.GET)
	public void getAssetDetail(Model model, Principal prinicpal, HttpServletRequest request, HttpServletResponse response) {
		String pattern = "MM/dd/yyyy";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
		LOGGER.debug("prinicpal : "+prinicpal);
		UserProfile userProfile = null;
		StringBuffer buffer = new StringBuffer();
		ContextAuthenticatedUserDetailsVO loggedInUser = getCurrentUser();
		LOGGER.debug("loggedInUser : "+loggedInUser);
		if(loggedInUser != null){
			UserProfile profile = loggedInUser.getProfile();
			LOGGER.debug("profile : "+profile);
			//userProfile = userProfileService.findByIdWithAsset(profile.getId());
		}
		
		if(prinicpal != null){
			String useName = prinicpal.getName();
			LOGGER.debug("useName : "+useName);
		}
		
		String assetId = request.getParameter("assetId");
		Integer asset_id = Integer.parseInt(assetId);
		Asset asset =assetService.findById(asset_id);
		
		try {
			buffer.append("assetId");
			buffer.append("***");
			buffer.append(asset.getId());
			buffer.append("###");
			
			buffer.append("assetName");
			buffer.append("***");
			buffer.append(asset.getAssetName());
			buffer.append("###");
			
			buffer.append("assetShortDescription");
			buffer.append("***");
			buffer.append(asset.getAssetShortDescription());
			buffer.append("###");
			
			buffer.append("projectName");
			buffer.append("***");
			buffer.append(asset.getProjectName());
			buffer.append("###");
			
			buffer.append("registeredInAssetPortal");
			buffer.append("***");
			buffer.append(asset.getRegisteredInAssetPortal());
			buffer.append("###");
			
			buffer.append("reusedInOtherProjectsName");
			buffer.append("***");
			buffer.append(asset.getReusedInOtherProjectsName());
			buffer.append("###");
			
			buffer.append("effortSave");
			buffer.append("***");
			buffer.append(asset.getEffortSave());
			buffer.append("###");
			
			buffer.append("creationDate");
			buffer.append("***");
			buffer.append(format.format(asset.getCreationDate()));
			buffer.append("###");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			PrintWriter out = response.getWriter();
			out.print(buffer.toString());
			out.close();
			
		} catch (Exception e) {
			LOGGER.debug("getAssetDetail exception : " + e.getStackTrace());
		}
		
		//model.addAttribute(Constants.ASSET_LIST, userProfile.getAssets());
		return ; // "protected/asset";
	}
	
	
	
	@Autowired
	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	@Autowired
	public void setAssetService(AssetService assetService) {
		this.assetService = assetService;
	}
	
	@RequestMapping(value="removeAssetDetail.html", method=RequestMethod.POST)
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
	
	
	
}
