package com.sprint3.backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "student_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "group_name", columnDefinition = "VARCHAR(50)")
    private String groupName;

    @Column(name = "quantity", columnDefinition = "BIGINT")
    private String quantity;

    @Column(name = "file_url", columnDefinition = "VARCHAR(250)")
    private String fileURL;

    // relationship

    @OneToMany(mappedBy = "studentGroup", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private java.util.List<Student> studentList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private Teacher teacher;

    @OneToOne(mappedBy = "studentGroup", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private CheckThesis checkThesis;
}
