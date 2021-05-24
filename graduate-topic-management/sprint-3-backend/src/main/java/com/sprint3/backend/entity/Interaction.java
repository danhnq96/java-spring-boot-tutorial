package com.sprint3.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "interaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR(50)")
    private String title;

    @Column(name = "content", columnDefinition = "VARCHAR(250)")
    private String content;

    // relationship

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private Student student;
}
