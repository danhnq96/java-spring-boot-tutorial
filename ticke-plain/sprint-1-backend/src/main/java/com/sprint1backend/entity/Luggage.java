package com.sprint1backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "luggage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Luggage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(40)")
    private String name;

    @Column(name = "price", columnDefinition = "DOUBLE")
    private Double price;

//    ------

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Passenger> passenger;
}
