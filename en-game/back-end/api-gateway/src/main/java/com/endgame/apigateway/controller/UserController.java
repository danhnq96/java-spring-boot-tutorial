package com.endgame.apigateway.controller;

import com.endgame.apigateway.exception.ResourceNotFoundException;
import com.endgame.apigateway.entity.User;
import com.endgame.apigateway.payload.request.LoginRequest;
import com.endgame.apigateway.payload.request.UpdateUserRequest;
import com.endgame.apigateway.repository.UserRepository;
import com.endgame.apigateway.security.CurrentUser;
import com.endgame.apigateway.security.UserPrincipal;
import com.endgame.apigateway.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  @GetMapping("/user/me")
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
    return userRepository.findById(userPrincipal.getId())
      .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
  }

  @PutMapping("/user/me")
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public User updateCurrentUser(@CurrentUser UserPrincipal userPrincipal,
                                @Valid @RequestBody UpdateUserRequest updateUserRequest) {
    updateUserRequest.setId(userPrincipal.getId());
    return userService.updateUser(updateUserRequest)
      .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
  }
}
