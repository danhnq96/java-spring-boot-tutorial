package com.endgame.addressservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/16
 * @project: auth-service
 */
@Entity
@Table(name = "billing_addresses", uniqueConstraints = {
  @UniqueConstraint(columnNames = "email"),
  @UniqueConstraint(columnNames = "user_id"),
  @UniqueConstraint(columnNames = "phone"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingAddress {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "first_name", nullable = false, columnDefinition = "nvarchar(50)")
  private String firstName;

  @Column(name = "mid_name", columnDefinition = "nvarchar(50)")
  private String midName;

  @Column(name = "last_name", nullable = false, columnDefinition = "nvarchar(50)")
  private String lastName;

  @Column(name = "company", columnDefinition = "nvarchar(150)")
  private String company;

  @Column(name = "email", nullable = false, columnDefinition = "nvarchar(150)")
  private String email;

  @Column(name = "phone", nullable = false, columnDefinition = "nvarchar(50)")
  private String phone;

  @Column(name = "country", nullable = false, columnDefinition = "nvarchar(150)")
  private String country;

  @Column(name = "city", nullable = false, columnDefinition = "nvarchar(150)")
  private String city;

  @Column(name = "province", columnDefinition = "nvarchar(250)")
  private String province;

  @Column(name = "postal_code", nullable = false, columnDefinition = "nvarchar(50)")
  private String postalCode;

  @Column(name = "address", nullable = false, columnDefinition = "nvarchar(250)")
  private String address;

}
