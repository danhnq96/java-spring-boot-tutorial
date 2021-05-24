package com.sprint2.backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "class_requirement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "class_name", columnDefinition = "VARCHAR(50)")
    private String className;

    // relationship

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_requirement_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private CourseRequirement courseRequirement;

    @OneToMany(mappedBy = "classRequirement", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private List<Student> studentList;
}
