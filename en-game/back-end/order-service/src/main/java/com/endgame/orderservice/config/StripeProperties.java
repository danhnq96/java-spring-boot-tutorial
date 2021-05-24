package com.endgame.orderservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/20
 * @project: order-service
 */
@ConfigurationProperties(prefix = "stripe")
public class StripeProperties {
  private final String secretKey;
  private final String publicKey;
  private final String webHookSecret;

  public StripeProperties(@Value("${stripe.secretKey}") String secretKey,
                          @Value("${stripe.publicKey}") String publicKey,
                          @Value("${stripe.webHookSecret}") String webHookSecret) {
    this.secretKey = secretKey;
    this.publicKey = publicKey;
    this.webHookSecret = webHookSecret;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public String getWebHookSecret() {
    return webHookSecret;
  }
}
