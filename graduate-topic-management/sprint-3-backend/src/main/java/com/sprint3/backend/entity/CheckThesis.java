package com.sprint3.backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "check_thesis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckThesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "check_date", columnDefinition = "DATETIME")
    private LocalDateTime checkDate;

    @Column(name = "status", columnDefinition = "BIT")
    private Boolean status = false;

    // relationship

    @OneToOne
    @JoinColumn(name = "thesis_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private Thesis thesis;

    @OneToOne(mappedBy = "checkThesis", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private ThesisDetail thesisDetail;

    @OneToOne
    @JoinColumn(name = "student_group_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private StudentGroup studentGroup;
}
