package com.sprint3.backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "thesis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Thesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "statement", columnDefinition = "VARCHAR(50)")
    private String statement;

    @Column(name = "amount", columnDefinition = "BIGINT")
    private String amount;

    @Column(name = "description", columnDefinition = "VARCHAR(250)")
    private String description;

    @Column(name = "create_date", columnDefinition = "DATETIME")
    private LocalDateTime createDate = LocalDateTime.now();

    // relationship

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private Teacher teacher;

    @OneToOne(mappedBy = "thesis", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private CheckThesis checkThesis;
}
