/**
 * 
 */
package com.ericsson.v1.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.Configuration;

/**
 * @author iqbal.hosain.khan@ericsson.com
 * 
 */
public class Constants {
	public static final String EJB_LOOKUP_CONTEXT = "java:app/ContextApps-ejb";
	public static final String EJB_LOOKUP_PATH = EJB_LOOKUP_CONTEXT + "/";
	public static final String SPACE = " ";
	public static final String BLANK = "";
	public static final String COMMA = ",";
	
	private static final Configuration CONFIG = AdaptersConfiguration
			.getInstance().getConfiguration();
	
		public static final Map<String, String> APPUSER2TWITTER_MAP = new HashMap<String, String>();
	public static final Map<String, String> APPUSER2FACEBOOK_MAP = new HashMap<String, String>();
	static {
		/*APPUSER2TWITTER_MAP.put(APPUSER_FIRSTNAME_KEY, TWITTER_NAME_KEY);
		APPUSER2TWITTER_MAP.put(APPUSER_USERNAME_KEY, TWITTER_USERNAME_KEY);

		APPUSER2FACEBOOK_MAP.put(APPUSER_FIRSTNAME_KEY, FACEBOOK_FIRSTNAME_KEY);
		APPUSER2FACEBOOK_MAP.put(APPUSER_LASTNAME_KEY, FACEBOOK_LASTNAME_KEY);
		APPUSER2FACEBOOK_MAP.put(APPUSER_EMAIL_KEY, FACEBOOK_EMAIL_KEY);*/
	}

	public static final String UNCHECKED_COMPILER_WARNING = "unchecked";

	public static enum APP_ROLES {
		ROLE_CAS_AUTH_GUEST, ROLE_ADMIN, ROLE_SYSADMIN, ROLE_USER, ROLE_MANAGER
	};

	public static final String UTF_CHAR_ENC = "UTF-8";

	
	//public static final String CONSUMER_CAS_URL = AppConfiguration.get("Consumer_Cas_Url");

	public static final String SYSADMIN_EMAIL_KEY = "sysadmin_email";
	public static final String SYSADMIN_PASSWORD_KEY = "sysadmin_password";
	
	public static final String NEWLINE = "\n";
	
	
	public static final String BASE_PROTECTED_URL_PATH = "/v1/protected/";
	public static final String BASE_PUBLIC_URL_PATH = "/v1/public/";
	public static final String BASE_URL_PATH_SUFFIX = ".html";
	
	
	public final static String ASSET_LIST = "assetlist";
	public final static String DELIVERY_QUALITIY_LIST = "deliveryQualitiylist";
	public final static String OPERATIONAL_DISCIPLINE_LIST = "operationalDisciplineList";
	public final static String KPI_LIST = "kpilist";
	public final static String KPI_LIST1 = "kpilist1";
	
	/*CONFIG.getProperty("ipm.setupbootstrap.data"); */
	
	/*ipm.controller.registrationController.showRegistrationForm.uri=registration
	ipm.controller.registrationController.registerUserAccount.uri=registration
	ipm.controller.registrationController.resendRegistrationToken.uri=resendRegistrationToken
	ipm.controller.registrationController.regitrationConfirm.uri=regitrationConfirm*/
	public final static String IPM_CONTROLLER_URL_SUFFIX=".html";
	public final static String IPM_CONTROLLER_REGISTRATIONCONTROLLER_SHOWREGISTRATIONFORM_URI="registration";
	public final static String IPM_CONTROLLER_REGISTRATIONCONTROLLER_REGISTERUSERACCOUNT_URI="registration";
	public final static String IPM_CONTROLLER_REGISTRATIONCONTROLLER_RESENDREGISTRATIONTOKEN_URI="resendRegistrationToken";
	public final static String IPM_CONTROLLER_REGISTRATIONCONTROLLER_REGITRATIONCONFIRM_URI="regitrationConfirm";
	


}
