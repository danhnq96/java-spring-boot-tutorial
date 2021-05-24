package com.endgame.orderservice.payload.request;

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
public class GetCartsRequest {
  private Long userId;
}
