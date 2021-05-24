package com.sprint3.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "instruction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "file_name", columnDefinition = "VARCHAR(50)")
    private String fileName;

    @Column(name = "description", columnDefinition = "VARCHAR(250)")
    private String description;
}
