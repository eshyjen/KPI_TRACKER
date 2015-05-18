package com.ericsson.ipm.v1.security.authentication.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class CustomContexSimpleUrlLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    //private final Logger logger = LoggerFactory.getLogger(CustomContexSimpleUrlLogoutSuccessHandler.class);
    
    
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {
        System.out.println("logout successhandler: "+request.getRemoteAddr());
        super.onLogoutSuccess(request, response, authentication);
    }
}
