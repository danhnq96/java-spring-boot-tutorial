package com.sprint3.backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "full_name", columnDefinition = "VARCHAR(50)")
    private String fullName;

    @Column(name = "teacher_code", columnDefinition = "VARCHAR(50)")
    private String teacherCode;

    @Column(name = "email", columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(name = "identity_number", columnDefinition = "VARCHAR(50)")
    private String identityNumber;

    @Column(name = "phone", columnDefinition = "VARCHAR(50)")
    private String phone;

    // relationship

    @OneToOne
    @JoinColumn(name = "app_account_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private AppAccount appAccount;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private List<StudentGroup> studentGroupList;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private List<Thesis> thesisList;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private List<News> newsList;
}
