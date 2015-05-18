package com.ericsson.ipm.v1.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.ericsson.ipm.v1.security.authentication.vo.ContextAuthenticatedUserDetailsVO;

public abstract class BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	
	public ContextAuthenticatedUserDetailsVO getCurrentUser()
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOGGER.debug("principal : "+principal);
	    if (principal instanceof UserDetails) 
	    {
	    	String signum = ((UserDetails) principal).getUsername();
	    	LOGGER.debug("signum : "+signum);
	    	return (ContextAuthenticatedUserDetailsVO)principal;
	    }

	    return null;
	}
}
