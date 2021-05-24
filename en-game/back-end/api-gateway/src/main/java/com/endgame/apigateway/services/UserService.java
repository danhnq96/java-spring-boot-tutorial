package com.endgame.apigateway.services;

import com.endgame.apigateway.entity.User;
import com.endgame.apigateway.exception.ResourceNotFoundException;
import com.endgame.apigateway.payload.request.UpdateUserRequest;
import com.endgame.apigateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/15
 * @project: auth-service
 */
@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Transactional
  public Optional<User> updateUser(UpdateUserRequest userRequest) {
    User user = userRepository.findById(userRequest.getId()).orElseThrow(
      () -> new ResourceNotFoundException("User", "id", userRequest.getId())
    );

    if (userRequest.getPassword() != null) {
      user.setPassword(userRequest.getPassword());
    }

    user.setName(userRequest.getName());
    user.setEmail(userRequest.getEmail());

    return Optional.of(userRepository.save(user));
  }
}
