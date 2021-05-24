package com.endgame.adminservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Product  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "categoryId")
    private Long categoryId;

    @Column(name = "newPrice")
    private BigDecimal newPrice;

    @Column(name = "oldPrice")
    private BigDecimal oldPrice;

    @Column(name = "discount", columnDefinition = "numeric(4,2)")
    private BigDecimal discount;

    @Column(name = "cartCount")
    private Long cartCount;

    @Column(name = "weight", columnDefinition = "numeric(10,2)")
    private BigDecimal weight;

    @Column(name = "size")
    private String size;

    @Column(name = "description")
    private String description;

    @Column(name = "ratingCount")
    private BigDecimal ratingCount;

    @Column(name = "ratingValue")
    private BigDecimal ratingValue;

    @Column(name = "availibilityCount")
    private Long availibilityCount;

    @Column(name = "createdDate")
    private String createdDate;

    @Column(name = "updatedDate")
    private String updatedDate;

    @Column(name = "active")
    private boolean active;
}
