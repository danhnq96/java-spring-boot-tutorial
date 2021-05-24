package com.endgame.orderservice.model;

import com.endgame.orderservice.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/28
 * @project: order-service
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  private Long id;
  private Long categoryId;
  private String name;
  private Collection<Image> images;
  private BigDecimal oldPrice;
  private BigDecimal newPrice;
  private BigDecimal discount;
  private BigDecimal ratingsCount;
  private BigDecimal ratingsValue;
  private BigDecimal description;
  private int availableCount;
  private int cartCount;
  private List<String> color;
  private List<String> size;
  private BigDecimal weight;
}
