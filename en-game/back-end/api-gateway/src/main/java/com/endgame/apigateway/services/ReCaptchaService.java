package com.endgame.apigateway.services;

import com.endgame.apigateway.config.GoogleProperties;
import com.endgame.apigateway.payload.response.ReCaptchaResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/14
 * @project: auth-service
 */
@Service
public class ReCaptchaService {
  private final RestTemplate restTemplate;
  private final GoogleProperties googleProperties;

  public ReCaptchaService(RestTemplateBuilder restTemplateBuilder, GoogleProperties googleProperties) {
    this.restTemplate = restTemplateBuilder.build();
    this.googleProperties = googleProperties;
  }

  public boolean verify(String response) {
    MultiValueMap param = new LinkedMultiValueMap<>();
    param.add("secret", googleProperties.getReCaptCha().getSecretKey());
    param.add("response", response);

    ReCaptchaResponse recaptchaResponse = null;
    try {
      recaptchaResponse = this.restTemplate.postForObject(googleProperties.getReCaptCha().getVerifyURL(), param,
        ReCaptchaResponse.class);
    } catch (RestClientException e) {
      System.out.print(e.getMessage());
    }

    return recaptchaResponse.isSuccess();
  }
}
