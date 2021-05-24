package com.sprint1backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "full_name", columnDefinition = "VARCHAR(40)")
    private String fullName;

    @Column(name = "birthday", columnDefinition = "DATE")
    private LocalDate birthday;

    @Column(name = "address", columnDefinition = "VARCHAR(200)")
    private String address;

    @Column(name = "email", columnDefinition = "VARCHAR(40)")
    private String email;

    @Column(name = "gender", columnDefinition = "BIT")
    private Boolean gender = false;

    @Column(name = "phone_number", columnDefinition = "VARCHAR(40)")
    private String phoneNumber;

    @Column(name = "user_rank", columnDefinition = "VARCHAR(40)")
    private String userRank;

//    -----------


    @ManyToOne
    @JoinColumn(name = "user_type_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private UserType userType;

    @OneToOne
    @JoinColumn(name = "app_account_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private AppAccount appAccount;
//    ---------------

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Invoice> invoiceList;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Booking> bookingList;
}
