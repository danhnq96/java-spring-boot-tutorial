package com.endgame.adminservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "small")
    private String small;

    @Column(name = "big")
    private String big;

    @Column(name = "medium")
    private String medium;

    @Column(name = "productId")
    private Long productId;

    @Column(name = "mainImage")
    private boolean mainImage;
}
