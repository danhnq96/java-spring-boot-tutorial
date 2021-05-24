package com.endgame.orderservice.entity;

import com.endgame.orderservice.model.Product;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/13
 * @project: order-service
 */
@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false)
  private Long productId;

  @Column(nullable = false)
  private Long categoryId;

  @Column(nullable = false, columnDefinition = "nvarchar(250)")
  private String name;

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "cart_images", joinColumns = @JoinColumn(name = "cart_id"))
  @Column(name = "images")
  private Collection<Image> images = new HashSet<>();

  @Column
  private BigDecimal oldPrice;

  @Column(nullable = false)
  private BigDecimal newPrice;

  @Column(nullable = false)
  private int cartCount;

  @Column(nullable = false)
  private int availibilityCount;

  @Column(columnDefinition = "Decimal(4,2)")
  private BigDecimal discount;

  @Column(nullable = false)
  private BigDecimal ratingsCount;

  @Column(nullable = false)
  private BigDecimal ratingsValue;

  @Column(columnDefinition = "nvarchar(MAX)")
  private String description;

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Date createdAt;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @UpdateTimestamp
  private Date updatedAt;
}
