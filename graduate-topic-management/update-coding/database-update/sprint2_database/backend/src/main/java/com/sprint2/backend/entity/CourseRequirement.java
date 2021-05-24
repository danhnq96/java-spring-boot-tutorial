package com.sprint2.backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "course_requirement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "course_name", columnDefinition = "VARCHAR(50)")
    private String courseName;

    // relationship

    @OneToMany(mappedBy = "courseRequirement", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private List<ClassRequirement> classRequirementList;
}
