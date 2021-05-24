package com.endgame.apigateway.controller;

import com.endgame.apigateway.payload.response.AuthResponse;
import com.endgame.apigateway.payload.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/01/30
 * @project: auth-service
 */
@RestController
@RequestMapping("/api/auth2")
public class Auth2Controller {
  @GetMapping("/callback/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

//    Authentication authentication = authenticationManager.authenticate(
//      new UsernamePasswordAuthenticationToken(
//        loginRequest.getEmail(),
//        loginRequest.getPassword()
//      )
//    );
//
//    SecurityContextHolder.getContext().setAuthentication(authentication);
//
//    String token = tokenProvider.createToken(authentication);
    return ResponseEntity.ok(new AuthResponse("Hello!!!"));
  }
}
