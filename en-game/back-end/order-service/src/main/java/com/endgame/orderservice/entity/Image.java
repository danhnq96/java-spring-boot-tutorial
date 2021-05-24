package com.endgame.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/13
 * @project: order-service
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Image {
  private String small;
  private String medium;
  private String large;
}
