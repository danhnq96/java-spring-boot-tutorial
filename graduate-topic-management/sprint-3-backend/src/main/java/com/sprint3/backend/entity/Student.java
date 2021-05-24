package com.sprint3.backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "full_name", columnDefinition = "VARCHAR(50)")
    private String fullName;

    @Column(name = "student_code", columnDefinition = "VARCHAR(50)")
    private String studentCode;

    @Column(name = "email", columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(name = "phone", columnDefinition = "VARCHAR(50)")
    private String phone;

    @Column(name = "position", columnDefinition = "BIT")
    private Boolean position = false;

    @Column(name = "teacher", columnDefinition = "VARCHAR(50)")
    private String teacher;

    @Column(name = "topic", columnDefinition = "VARCHAR(50)")
    private String topic;

    // relationship

    @OneToOne
    @JoinColumn(name = "app_account_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private AppAccount appAccount;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private java.util.List<Interaction> interactionList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private StudentGroup studentGroup;
}
