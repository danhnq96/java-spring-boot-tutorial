package com.endgame.apigateway.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private TokenProvider tokenProvider;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Autowired
  private RestTemplate restTemplate;

  private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      String jwt = getJwtFromRequest(request);
      String contextPath = request.getRequestURI();

      if (contextPath.contains("/api/admin")) {
        if (StringUtils.hasText(jwt)) {
          try {
            String username = jwtTokenUtil.getUsernameFromToken(jwt);
            ResponseEntity<AdminDetail> responseEntity = restTemplate.getForEntity("http://admin-service/api/admin" +
              "/get_user/" + username, AdminDetail.class);
            AdminDetail adminDetail = responseEntity.getBody();

            if (jwtTokenUtil.validateToken(jwt, adminDetail)) {
              UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(adminDetail,
                null, adminDetail.getAuthorities());
              authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

              SecurityContextHolder.getContext().setAuthentication(authentication);
            }
          } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
          } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
          }
        } else {
          logger.warn("JWT Token does not begin with Bearer String");
        }
      } else {
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
          Long userId = tokenProvider.getUserIdFromToken(jwt);
          UserDetails userDetails = customUserDetailsService.loadUserById(userId);
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
            null, userDetails.getAuthorities());
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    } catch (Exception ex) {
      logger.error("Could not set user authentication in security context", ex);
    }

    filterChain.doFilter(request, response);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7, bearerToken.length());
    }
    return null;
  }
}
