package com.ericsson.ipm.v1.security.authentication.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class CustomContextAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	 private final Logger LOGGER = LoggerFactory.getLogger(CustomContextAuthenticationFailureHandler.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		LOGGER.debug("exception getMessage "+exception.getMessage());
		LOGGER.debug("exception getCause "+exception.getCause());
		LOGGER.debug("exception getExtraInformation "+exception.getExtraInformation());
		LOGGER.debug("exception getStackTrace "+exception.getStackTrace());
		//LOGGER.debug("exception "+exception.initCause(exception));
		
		super.onAuthenticationFailure(request, response, exception);
	}
	
}
 