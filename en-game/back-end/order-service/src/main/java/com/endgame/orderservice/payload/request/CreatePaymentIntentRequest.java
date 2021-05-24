package com.endgame.orderservice.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/21
 * @project: order-service
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentIntentRequest {
  private BigDecimal amount;
}
