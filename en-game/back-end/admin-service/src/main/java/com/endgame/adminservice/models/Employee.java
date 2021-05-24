package com.endgame.adminservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee extends Person {

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Account account;

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "idCard")
    private String idCard;

}
