package com.endgame.apigateway.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/14
 * @project: auth-service
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReCaptchaResponse {
  @JsonProperty("success")
  private boolean success;

  @JsonProperty("challenge_ts")
  private String challengeTs;

  @JsonProperty("hostname")
  private String hostname;

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getChallengeTs() {
    return challengeTs;
  }

  public void setChallengeTs(String challengeTs) {
    this.challengeTs = challengeTs;
  }

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }
}
