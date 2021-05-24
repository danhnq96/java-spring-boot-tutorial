package com.endgame.apigateway.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/01/03
 * @project: SpringBootSocialJPA
 */

@Entity
@Table(name = "PersistentLogin")
public class PersistentLogin {
  @Id
  @Column(name = "series", length = 64, nullable = false)
  private String series;

  @Column(name = "userName", length = 64, nullable = false)
  private String userName;

  @Column(name = "token", length = 64, nullable = false)
  private String token;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "lastUsed", nullable = false)
  private Date lastUsed;

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createdAt;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date updatedAt;

  public String getSeries() {
    return series;
  }

  public void setSeries(String series) {
    this.series = series;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Date getLastUsed() {
    return lastUsed;
  }

  public void setLastUsed(Date lastUsed) {
    this.lastUsed = lastUsed;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
}
