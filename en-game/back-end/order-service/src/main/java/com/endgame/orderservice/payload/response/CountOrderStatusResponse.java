package com.endgame.orderservice.payload.response;

import com.endgame.orderservice.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/11
 * @project: order-service
 */
public interface CountOrderStatusResponse {
  OrderStatus getStatus();
  int getCount();
}
