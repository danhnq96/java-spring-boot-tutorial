package com.endgame.apigateway.entity;

import javax.persistence.*;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/01/03
 * @project: SpringBootSocialJPA
 */

@Entity
@Table(name = "AppRole", //
  uniqueConstraints = { //
    @UniqueConstraint(name = "APP_ROLE_UK", columnNames = "roleName") })
public class AppRole {
  public static final String ROLE_USER = "ROLE_USER";
  public static final String ROLE_ADMIN = "ROLE_ADMIN";

  @Id
  @GeneratedValue
  @Column(name = "roleId", nullable = false)
  private Long roleId;

  @Column(name = "roleName", length = 30, nullable = false)
  private String roleName;

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

}