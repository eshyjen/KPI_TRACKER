package com.ericsson.ipm.v1.security.authentication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ericsson.ipm.v1.domain.Role;
import com.ericsson.ipm.v1.domain.UserProfile;
import com.ericsson.ipm.v1.security.authentication.vo.ContextAuthenticatedUserDetailsVO;
import com.ericsson.ipm.v1.security.authentication.vo.CustomGrantedAuthority;
import com.ericsson.ipm.v1.service.UserProfileService;
import com.ericsson.v1.util.AdaptersConfiguration;
import com.ericsson.v1.util.Constants;




/**
* @author iqbal.hosain.khan@ericsson.com
*/

public class CustomContextUserDetailsService implements UserDetailsService {

	private final Logger LOGGER = LoggerFactory
			.getLogger(CustomContextUserDetailsService.class);
	
	private static final Configuration CONFIG = AdaptersConfiguration
			.getInstance().getConfiguration();

	private static final String ROLE_PREFIX = "ROLE_";

	private UserProfileService userProfileService;

	@Override
	public UserDetails loadUserByUsername(String authPrincipalIdentifier)
			throws UsernameNotFoundException {

		List<UserProfile> profiles = getUserProfileService().findBySignunidWithRole(
				authPrincipalIdentifier);
		UserProfile profile = null;
		if (profiles != null && profiles.size() > 0) {
			profile = profiles.get(0);
		}

		LOGGER.debug("profile : " + profile);
		
		Object isEnabled = CONFIG.getProperty("ipm.default.user.is_enabled");
		if(isEnabled != null && "false".equalsIgnoreCase(isEnabled.toString())){
			LOGGER.debug("profile.getIsEnabled() : " + profile.getIsEnabled());
			if(!profile.getIsEnabled()){
				profile = null;
			}
		}
		
		return (profile == null) ? getCASAuthenticatedGuest(authPrincipalIdentifier)
				: getRegisteredUser(profile);
	}

	private ContextAuthenticatedUserDetailsVO getCASAuthenticatedGuest(
			final String uuid) {
		return new ContextAuthenticatedUserDetailsVO(uuid,
				prepareAuthList(Constants.APP_ROLES.ROLE_CAS_AUTH_GUEST), null);
	}

	private Collection<? extends GrantedAuthority> prepareAuthList(
			Constants.APP_ROLES role) {
		return Arrays.asList(new CustomGrantedAuthority(role.toString()));
	}

	private ContextAuthenticatedUserDetailsVO getRegisteredUser(
			UserProfile profile) {
		Boolean is_manager = profile.getRole();
		List<Role> roles = profile.getRoles();
		if(is_manager){
			Role role = new Role();
			role.setCode("MANAGER");
			roles.add(role);
		}
		return new ContextAuthenticatedUserDetailsVO(profile.getSignunId(),
				profile.getPassword(),
				mapRoles2GrantedAuths(roles.toArray(new Role[roles.size()])),
				profile);
	}

	private Collection<? extends GrantedAuthority> mapRoles2GrantedAuths(
			Role[] roles) {
		List<CustomGrantedAuthority> auths = new ArrayList<CustomGrantedAuthority>();
		for (Role role : roles) {
			auths.add(new CustomGrantedAuthority(ROLE_PREFIX + role.getCode()));
		}
		return auths;
	}


	public UserProfileService getUserProfileService() {
		return userProfileService;
	}

	@Autowired
	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

}