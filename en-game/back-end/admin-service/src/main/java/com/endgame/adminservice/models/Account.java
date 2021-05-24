package com.endgame.adminservice.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Accounts")
@NoArgsConstructor
@Getter
@Setter
@Data
public class Account {
    @Id
    @Column(name = "employeeId")
    private int employeeId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "lastLogin")
    private String lastLogin;

//
//    public static void main(String[] args) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        System.out.println(bCryptPasswordEncoder.encode("admin"));
//    }
}
