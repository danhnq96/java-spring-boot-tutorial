package com.endgame.adminservice.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MemberTypes")
@Data
public class MemberType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "memberType")
    private List<Member> members;

    @Column(name = "levelId")
    private String levelId;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;

}
