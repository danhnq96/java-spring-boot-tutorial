package com.sprint1backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "employee_code", columnDefinition = "VARCHAR(40)")
    private String employeeCode;

    @Column(name = "full_name", columnDefinition = "VARCHAR(40)")
    private String fullName;

    @Column(name = "birthday", columnDefinition = "DATE")
    private LocalDate birthday;

    @Column(name = "address", columnDefinition = "VARCHAR(255)")
    private String address;

    @Column(name = "email", columnDefinition = "VARCHAR(40)")
    private String email;

    @Column(name = "gender", columnDefinition = "BIT")
    private boolean gender;

    @Column(name = "phone_number", columnDefinition = "VARCHAR(40)")
    private String phoneNumber;

    @Column(name = "change_pass_token", columnDefinition = "VARCHAR(80)")
    private String changePassToken;

    //    ----------
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "app_account_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private AppAccount appAccount;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Ticket> ticketList;
}
