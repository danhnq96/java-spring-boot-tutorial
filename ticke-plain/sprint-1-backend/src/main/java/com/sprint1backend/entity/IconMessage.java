package com.sprint1backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="icon")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class IconMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name="name", columnDefinition = "VARCHAR(255)")
    private String name;
}
