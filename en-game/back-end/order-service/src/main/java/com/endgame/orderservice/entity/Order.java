package com.endgame.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/22
 * @project: order-service
 */
@Entity
@Table(name = "orders", uniqueConstraints = {
  @UniqueConstraint(columnNames = "order_code")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "order_code", nullable = false, columnDefinition = "nvarchar(50)")
  private String orderCode;

  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false)
  private Long shippingId;

  @Column(nullable = false)
  private Long employeeId;

  @Column(nullable = false)
  private BigDecimal amount;

  @Column(nullable = false)
  private BigDecimal discount;

  @Column(nullable = false)
  private BigDecimal tax;

  @Column(nullable = false)
  private BigDecimal shippingFee;

  @Column(nullable = true)
  private String paymentId;

  @Column(nullable = false)
  private OrderStatus status;

  @OneToMany(mappedBy = "order")
  @JsonManagedReference // Chống lỗi đệ quy JSON khi trả về list objects có chứa list
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Collection<OrderDetail> orderDetails;

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Date createdAt;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @UpdateTimestamp
  private Date updatedAt;

}
