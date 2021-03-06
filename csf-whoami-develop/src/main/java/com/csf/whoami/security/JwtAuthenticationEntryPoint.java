package com.csf.whoami.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -7858869558953243875L;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {

        final String error = (String) request.getAttribute("error");
        System.out.println(error);
        try {
            if (error != null) {
                LOGGER.info(error);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, error);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized or invalid login details");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}