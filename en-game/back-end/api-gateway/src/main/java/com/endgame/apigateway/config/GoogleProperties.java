package com.endgame.apigateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/14
 * @project: auth-service
 */
@ConfigurationProperties(prefix = "google")
public class GoogleProperties {
  private final ReCaptCha reCaptCha = new ReCaptCha();

  public static class ReCaptCha {
    private String secretKey;
    private String verifyURL;

    public String getSecretKey() {
      return secretKey;
    }

    public void setSecretKey(String secretKey) {
      this.secretKey = secretKey;
    }

    public String getVerifyURL() {
      return verifyURL;
    }

    public void setVerifyURL(String verifyURL) {
      this.verifyURL = verifyURL;
    }
  }

  public ReCaptCha getReCaptCha() {
    return reCaptCha;
  }
}
