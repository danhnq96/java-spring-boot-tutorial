package com.endgame.orderservice.payload.request;

import com.endgame.orderservice.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/14
 * @project: order-service
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCartRequest {
  private Long userId;
  private Cart cart;
}
