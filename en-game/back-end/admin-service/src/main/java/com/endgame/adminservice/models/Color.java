package com.endgame.adminservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Colors")
@NoArgsConstructor
@Data
public class Color implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "productId")
    private Long productId;

    @Column(name = "name")
    private String name;
}
