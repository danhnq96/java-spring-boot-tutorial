package com.endgame.orderservice.payload.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/21
 * @project: order-service
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse<T> {
  private String message;
  private HttpStatus status;
  private T data;
}
