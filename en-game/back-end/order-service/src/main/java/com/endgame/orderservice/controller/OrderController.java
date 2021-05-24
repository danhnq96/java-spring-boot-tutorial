package com.endgame.orderservice.controller;

import com.endgame.orderservice.entity.Order;
import com.endgame.orderservice.payload.request.GetOrdersRequest;
import com.endgame.orderservice.payload.response.CreatePaymentIntentResponse;
import com.endgame.orderservice.payload.response.GetOrdersResponse;
import com.endgame.orderservice.payload.response.SuccessResponse;
import com.endgame.orderservice.service.OrderService;
import com.stripe.exception.StripeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.QueryParam;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/16
 * @project: address-service
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

  private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

  @Autowired
  OrderService orderService;

  @GetMapping("/")
  public ResponseEntity<?> getAllOrder(@Valid @RequestParam("userId") String userId,
                                       @Valid @RequestParam("pageNumber") String pageNumber,
                                       @Valid @Min(1) @RequestParam("pageSize") String pageSize) {
    GetOrdersRequest getOrdersRequest = new GetOrdersRequest(Long.parseLong(userId), Integer.parseInt(pageNumber),
      Integer.parseInt(pageSize));
    GetOrdersResponse response = orderService.getOrdersPagination(getOrdersRequest);

    return ResponseEntity.ok(new SuccessResponse<>("Get orders successfully", HttpStatus.OK,
      response));
  }

  @GetMapping("/count_order_status/")
  public ResponseEntity<?> getCountOrderStatus(@Valid @RequestParam("userId") Long userId) {
    return ResponseEntity.ok(new SuccessResponse<>("Get count order status successfully", HttpStatus.OK,
      orderService.getCountOrderStatus(userId)));
  }

  @PostMapping("/{orderId}/cancel")
  public ResponseEntity<?> cancelOrderById(@Valid @PathVariable("orderId") Long orderId) throws StripeException {
    return ResponseEntity.ok(new SuccessResponse<>("Get count order status successfully", HttpStatus.OK,
      orderService.cancelOrderById(orderId)));
  }
}
