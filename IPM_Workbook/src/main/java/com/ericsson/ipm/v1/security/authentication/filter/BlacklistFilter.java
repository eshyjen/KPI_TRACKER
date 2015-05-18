package com.ericsson.ipm.v1.security.authentication.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class BlacklistFilter extends OncePerRequestFilter {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(BlacklistFilter.class);

	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		// Retrieve user details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Filter only if user details is not empty; otherwise there's nothing to filter
        if (authentication != null) {
        	
        	// If the username is equal to mike, deny access
	        /*if (authentication.getName().equals("mike") == true ) {
	        	logger.error("Username and password match. Access denied!");
	            throw new AccessDeniedException("Username and password match. Access denied!");
	        }*/
        	
        	LOGGER.debug("authentication.getPrincipal(); "+authentication.getPrincipal());
			
			LOGGER.debug("authentication.getName(); "+authentication.getName());
			
			LOGGER.debug("authentication.getCredentials(); "+authentication.getCredentials());
			
			LOGGER.debug("authentication.getDetails(); "+authentication.getDetails());
			
			LOGGER.debug("authentication.getAuthorities(); "+authentication.getAuthorities());
	        
        }
        
        // User details are not empty
        filterChain.doFilter(request, response);
	}

}
