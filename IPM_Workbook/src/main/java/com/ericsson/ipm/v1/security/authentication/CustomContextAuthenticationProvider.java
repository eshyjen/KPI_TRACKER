package com.ericsson.ipm.v1.security.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.ericsson.ipm.v1.security.authentication.handler.CustomContextAuthenticationSuccessHandler;

public class CustomContextAuthenticationProvider extends DaoAuthenticationProvider {

	
	private final Logger LOGGER = LoggerFactory.getLogger(CustomContextAuthenticationProvider.class);
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		
		try {
			
			LOGGER.debug("authentication.getPrincipal(); "+authentication.getPrincipal());
			
			LOGGER.debug("authentication.getName(); "+authentication.getName());
			
			LOGGER.debug("authentication.getCredentials(); "+authentication.getCredentials());
			
			LOGGER.debug("authentication.getDetails(); "+authentication.getDetails());
			
			LOGGER.debug("authentication.getAuthorities(); "+authentication.getAuthorities());
			
			//ContextAuthenticatedUserDetailsVO authUserDetails = (ContextAuthenticatedUserDetailsVO) super.authenticate(authentication);
			//authUserDetails.setPrincipal(new UserPrincipalVO(attributePrincipal.getName(), attributePrincipal.getAttributes()));

			Authentication auth = super.authenticate(authentication);
			
			
			LOGGER.debug("after authentication.getPrincipal(); "+authentication.getPrincipal());
			
			LOGGER.debug("after authentication.getName(); "+authentication.getName());
			
			LOGGER.debug("after authentication.getCredentials(); "+authentication.getCredentials());
			
			LOGGER.debug("after authentication.getDetails(); "+authentication.getDetails());
			
			LOGGER.debug("after authentication.getAuthorities(); "+authentication.getAuthorities());

			// if reach here, means login success, else exception will be thrown
			// reset the user_attempts
			//userDetailsDao.resetFailAttempts(authentication.getName());

			return auth;

		} catch (BadCredentialsException e) {

			//userDetailsDao.updateFailAttempts(authentication.getName());
			throw e;

		} catch (LockedException e) {

			/*String error = "";
			UserAttempts userAttempts = userDetailsDao.getUserAttempts(authentication.getName());
			if (userAttempts != null) {
				Date lastAttempts = userAttempts.getLastModified();
				error = "User account is locked! <br><br>Username : " + authentication.getName()
						+ "<br>Last Attempts : " + lastAttempts;
			} else {
				error = e.getMessage();
			}*/

			//throw new LockedException(error);
			throw new LockedException(e.getMessage());
		}

		
		//return super.authenticate(authentication);
	}
}
