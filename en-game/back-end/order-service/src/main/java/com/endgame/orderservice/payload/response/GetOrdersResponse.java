package com.endgame.orderservice.payload.response;

import com.endgame.orderservice.entity.Order;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.Collection;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/04
 * @project: order-service
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class GetOrdersResponse {
  @JsonProperty(value = "page", required = true)
  private Page<Order> page;
}
