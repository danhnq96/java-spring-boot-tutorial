package com.sprint3.backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "app_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "username", columnDefinition = "VARCHAR(50)")
    private String username;

    @Column(name = "password", columnDefinition = "VARCHAR(255)")
    private String password;

    // 2 properties for email verification
    @Column(name = "enabled", columnDefinition = "BIT")
    private Boolean enabled = false;

    @Column(name = "verification_code", columnDefinition = "VARCHAR(64)")
    private String verificationCode;

    // relationship

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "app_role_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private AppRole appRole;

    @OneToOne(mappedBy = "appAccount", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private AppAdmin appAdmin;

    @OneToOne(mappedBy = "appAccount", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private Teacher teacher;

    @OneToOne(mappedBy = "appAccount", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private Student student;

    @OneToMany(mappedBy = "appAccount", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private java.util.List<Notification> notificationList;
}
