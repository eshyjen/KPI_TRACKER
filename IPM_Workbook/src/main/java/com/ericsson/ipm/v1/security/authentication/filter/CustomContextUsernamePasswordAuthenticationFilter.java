package com.ericsson.ipm.v1.security.authentication.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomContextUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    
    private final Logger LOGGER = LoggerFactory.getLogger(CustomContextUsernamePasswordAuthenticationFilter.class);
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LOGGER.debug("login attempt from:" + request.getRemoteAddr());
        Authentication authentication = super.attemptAuthentication(request, response);
        return authentication;
    }
    
}
