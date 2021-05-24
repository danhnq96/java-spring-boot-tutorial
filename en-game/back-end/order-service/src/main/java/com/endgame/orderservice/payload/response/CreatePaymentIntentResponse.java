package com.endgame.orderservice.payload.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stripe.model.PaymentIntent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/03
 * @project: order-service
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentIntentResponse {
  private String clientSecret;
  private Long orderId;
}
