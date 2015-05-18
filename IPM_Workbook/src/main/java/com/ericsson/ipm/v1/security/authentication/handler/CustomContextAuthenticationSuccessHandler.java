package com.ericsson.ipm.v1.security.authentication.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.ericsson.ipm.v1.security.authentication.vo.ContextAuthenticatedUserDetailsVO;
import com.ericsson.v1.util.Constants.APP_ROLES;



public class CustomContextAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	 private final Logger LOGGER = LoggerFactory.getLogger(CustomContextAuthenticationSuccessHandler.class);

	public static final String SESSION_USERPRINCIPAL_KEY = "_USER_PRINCIPAL_KEY";
	public static final String SESSION_USERPROFILE_KEY = "_USER_PROFILE_KEY";
	public static final String SESSION_ISADMIN_KEY = "_IS_ADMIN";

	private String defaultGuestUrl;
	private String defaultAuthUrl;
	private String defaultApprovalPendingUrl;
	private String defaultSysAdminUrl;


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		ContextAuthenticatedUserDetailsVO user = (ContextAuthenticatedUserDetailsVO) authentication
				.getPrincipal();
		HttpSession session = request.getSession();
		session.setAttribute(SESSION_USERPRINCIPAL_KEY, user.getPrincipal());
		session.setAttribute(SESSION_USERPROFILE_KEY, user.getProfile());
		session.setAttribute(SESSION_ISADMIN_KEY,user.isUserInRole(APP_ROLES.ROLE_ADMIN));
		LOGGER.debug("user.getPrincipal() : " + user.getPrincipal());
		LOGGER.debug("user.getProfile() : " + user.getProfile());
		LOGGER.debug("user.isUserInRole(APP_ROLES.ROLE_ADMIN) : " + user.isUserInRole(APP_ROLES.ROLE_ADMIN));
		LOGGER.debug("user.isUserInRole(APP_ROLES.ROLE_CAS_AUTH_GUEST) : " + user.isUserInRole(APP_ROLES.ROLE_CAS_AUTH_GUEST));
		LOGGER.debug("user.isUserInRole(APP_ROLES.ROLE_SYSADMIN) : " + user.isUserInRole(APP_ROLES.ROLE_SYSADMIN));
		String targetUrl = null;
		if (user.isUserInRole(APP_ROLES.ROLE_CAS_AUTH_GUEST))
			targetUrl = getDefaultGuestUrl();
		//else if (user.isUserInRole(APP_ROLES.ROLE_UNAPPROVED_USER))
			//targetUrl = getDefaultApprovalPendingUrl();

		// full-access user, as per role
		else if (user.isUserInRole(APP_ROLES.ROLE_SYSADMIN))
			targetUrl = getDefaultSysAdminUrl();
		else
			targetUrl = getDefaultAuthUrl();

		LOGGER.debug("targetUrl : " + targetUrl);
		setDefaultTargetUrl(targetUrl);
		super.onAuthenticationSuccess(request, response, authentication);
	}

	public String getDefaultGuestUrl() {
		return defaultGuestUrl;
	}

	public void setDefaultGuestUrl(String defaultGuestUrl) {
		this.defaultGuestUrl = defaultGuestUrl;
	}

	/*public UserService getUserService() {
		return (UserService) ServiceLocator.getEJB(Constants.EJB_LOOKUP_PATH
				+ "UserServiceImpl");
	}*/

	public String getDefaultAuthUrl() {
		return defaultAuthUrl;
	}

	public void setDefaultAuthUrl(String defaultAuthUrl) {
		this.defaultAuthUrl = defaultAuthUrl;
	}

	public String getDefaultApprovalPendingUrl() {
		return defaultApprovalPendingUrl;
	}

	public void setDefaultApprovalPendingUrl(String defaultApprovalPendingUrl) {
		this.defaultApprovalPendingUrl = defaultApprovalPendingUrl;
	}

	public String getDefaultSysAdminUrl() {
		return defaultSysAdminUrl;
	}

	public void setDefaultSysAdminUrl(String defaultSysAdminUrl) {
		this.defaultSysAdminUrl = defaultSysAdminUrl;
	}
	
}
