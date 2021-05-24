package com.sprint1backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity(name = "app_admin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "full_name", columnDefinition = "VARCHAR(40)")
    private String fullName;

    @Column(name = "birthday", columnDefinition = "DATE")
    private LocalDate birthday;

    @Column(name = "address", columnDefinition = "VARCHAR(255)")
    private String address;

    @Column(name = "email", columnDefinition = "VARCHAR(40)")
    private String email;

    @Column(name = "gender", columnDefinition = "BIT")
    private Boolean gender;

    @Column(name = "phone_number", columnDefinition = "VARCHAR(40)")
    private String phoneNumber;

//    -------


    @OneToOne
    @JoinColumn(name = "app_account_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private AppAccount appAccount;
//    ---------------

}
