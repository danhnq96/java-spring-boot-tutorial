package com.csf.whoami.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//@Log4j2
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Autowired
//    private MessageSource messageSource;
//
//    @Autowired
//    private CacheManager cacheManager;
//
//    @Autowired
//    private AdminService adminService;


    private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        LOGGER.info("HttpServletRequest " + request.getServletPath());
//        if (request.getServletPath().contains("admin/")) {
////             get JWT at header
//            String token = jwtTokenUtil.resolveToken(request);
//////                    String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJic2kiLCJleHAiOjE2MDcwMjM1NTgsImlhdCI6MTYwNjk4NzU1OH0.OAex2w342L7d-hKTpJUkisMkxkGl_8s-xx22bO-4MgU";
////
////            if(!request.getServletPath().contains("/admin/login") || !request.getServletPath().contains("/login")) {
//////                if (token == null) {
//////                    response.sendError(HttpServletResponse.SC_FORBIDDEN,
//////                            messageSource.getMessage("api.permission.fail", new String[]{}, null));
//////                    return;
//////                }
//////            }
//
//            // useful token check
//            if (token != null && jwtTokenUtil.validateToken(token)) {
////                //  useful token get info
//                Authentication authentication = jwtTokenUtil.getAuthentication(token);
////                // Authentication save at SecurityContext
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }

        chain.doFilter(request, response);
    }
}
