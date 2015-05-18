package com.ericsson.ipm.v1.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ericsson.v1.util.Constants;


@Controller
@RequestMapping(Constants.BASE_PUBLIC_URL_PATH)
public class LoginLogoutController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginLogoutController.class);

	@RequestMapping(value="login.html", method=RequestMethod.GET)
	public String login() {
		LOGGER.debug("....... : ");
		return "public/login";
	}
	
	@RequestMapping(value="logout.html", method=RequestMethod.GET)
	public String logout() {
		LOGGER.debug("....... : ");
		return "public/login";
	}
	
}
