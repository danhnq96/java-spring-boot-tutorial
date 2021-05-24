package com.endgame.apigateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
  @UniqueConstraint(columnNames = "email")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Email
  @Column(nullable = false)
  private String email;

  private String imageUrl;

  @Column()
  private String activationCode;

  @JsonIgnore
  private String password;

  @NotNull
  @Enumerated(EnumType.STRING)
  private AuthProvider provider;

  private String providerId;

  @Column()
  private Boolean active = false;

  // Fix: org.hibernate.LazyInitializationExcept
  // set FetchType.EAGER
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Date createdAt;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @UpdateTimestamp
  private Date updatedAt;
}
