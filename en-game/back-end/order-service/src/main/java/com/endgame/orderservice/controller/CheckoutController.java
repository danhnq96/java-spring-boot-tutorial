package com.endgame.orderservice.controller;

import com.endgame.orderservice.entity.Order;
import com.endgame.orderservice.payload.response.CreatePaymentIntentResponse;
import com.endgame.orderservice.payload.response.SuccessResponse;
import com.endgame.orderservice.service.CheckoutService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/20
 * @project: order-service
 */
@RestController
@RequestMapping("/api/order/checkout/")
public class CheckoutController {

  @Autowired
  private CheckoutService checkoutService;

  @PostMapping("payment_intents")
  public ResponseEntity<?> createPaymentIntent(@Valid @RequestBody Order createPaymentIntentRequest) {
    try {
      CreatePaymentIntentResponse response = checkoutService.createPaymentIntent(createPaymentIntentRequest);

      return ResponseEntity.ok(new SuccessResponse<>("Create payment intent successfully", HttpStatus.OK,
        response));
    } catch (StripeException ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SuccessResponse<>(ex.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR, null));
    }
  }
}
