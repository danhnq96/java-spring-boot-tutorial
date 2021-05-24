package com.sprint3.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "full_name", columnDefinition = "VARCHAR(50)")
    private String fullName;

    @Column(name = "mail", columnDefinition = "VARCHAR(50)")
    private String mail;

    @Column(name = "address", columnDefinition = "VARCHAR(50)")
    private String address;

    // relationship

    @OneToOne
    @JoinColumn(name = "app_account_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private AppAccount appAccount;
}
