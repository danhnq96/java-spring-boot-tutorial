package com.endgame.adminservice.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Categories")
@NoArgsConstructor
@Getter
@Setter
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "parentId")
    private int parentId;

    @Column(name = "hasSubCategory")
    private boolean hasSubCategory;

    @Column(name = "active")
    private boolean active;
}
