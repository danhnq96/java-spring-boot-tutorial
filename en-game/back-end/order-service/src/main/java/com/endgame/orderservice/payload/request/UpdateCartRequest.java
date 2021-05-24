package com.endgame.orderservice.payload.request;

import com.endgame.orderservice.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/15
 * @project: order-service
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartRequest {
  private Cart cart;
}
