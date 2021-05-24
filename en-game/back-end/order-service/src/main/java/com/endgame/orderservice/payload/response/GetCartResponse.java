package com.endgame.orderservice.payload.response;

import com.endgame.orderservice.entity.Cart;

import java.util.Collection;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/14
 * @project: order-service
 */
public interface GetCartResponse {
  Collection<Cart> getCarts(Long userId);
}
