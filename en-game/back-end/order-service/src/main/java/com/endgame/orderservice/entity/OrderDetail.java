package com.endgame.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/22
 * @project: order-service
 */
@Entity
@Table(name = "order_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Many to One: Having many details in 1 order
  @ManyToOne
  @JsonBackReference // Chống lỗi đệ quy JSON khi trả về list objects có chứa list
  @JoinColumn(name = "order_id") // thông qua khóa ngoại order_id
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Order order;

//  @Column(nullable = false)
//  private Long orderId;

  @Column(nullable = false)
  private Long productId;

  @Column(nullable = false)
  private Long categoryId;

  @Column(nullable = false, columnDefinition = "nvarchar(50)")
  private String name;

//  @Column(nullable = false)
//  @ElementCollection
//  private Collection<String> images;

  @Column(columnDefinition = "Decimal(10,4)")
  private BigDecimal oldPrice;

  @Column(nullable = false)
  private BigDecimal newPrice;

  @Column(nullable = false)
  private int cartCount;

  @Column(nullable = false)
  private int availibilityCount;

  @Column(columnDefinition = "Decimal(2,2)")
  private BigDecimal discount;

  @Column(nullable = false)
  private BigDecimal ratingsCount;

  @Column(nullable = false)
  private BigDecimal ratingsValue;

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Date createdAt;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @UpdateTimestamp
  private Date updatedAt;

  @PrePersist
  void prePersist() {
    if (this.oldPrice == null) {
      this.oldPrice = new BigDecimal(0);
    }

    if (this.discount == null) {
      this.discount = new BigDecimal(0);
    }
  }
}
