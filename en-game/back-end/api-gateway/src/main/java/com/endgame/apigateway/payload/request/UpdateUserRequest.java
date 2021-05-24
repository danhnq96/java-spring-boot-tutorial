package com.endgame.apigateway.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/15
 * @project: auth-service
 */
public class UpdateUserRequest {
  private long id;
  private String name;
  private String password;

  @Email
  private String email;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
