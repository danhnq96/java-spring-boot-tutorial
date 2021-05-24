package com.csf.whoami.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractController {

    @Autowired
    private AuthenticationManager authenticationManager;

    public Authentication authenticate(String username, String password) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

    public String getUserAgent(HttpServletRequest request) {
        String ua = "";
        if (request != null) {
            ua = request.getHeader("TbUser-Agent");
        }
        return ua;
    }

    protected PageRequest PageDescCreatedAt(Integer page, Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 10;
        }
        return PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
    }

}
