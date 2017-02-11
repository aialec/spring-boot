package com.aialec.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class RESTAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	private static Logger logger = LoggerFactory.getLogger(RESTAuthenticationEntryPoint.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		logger.info("here " + RESTAuthenticationEntryPoint.class);
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
}
