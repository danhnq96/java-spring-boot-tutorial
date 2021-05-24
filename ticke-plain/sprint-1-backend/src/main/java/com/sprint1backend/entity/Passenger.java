package com.sprint1backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "passenger")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
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

    @Column(name = "identity_number", columnDefinition = "VARCHAR(40)")
    private String identityNumber;




    //    ----
    @ManyToOne
    @JoinColumn(name = "luggage_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private Luggage luggage;

    @ManyToOne
    @JoinColumn(name = "passenger_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private Passenger passenger;
//-------

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Passenger> passengerList;

    @OneToOne(mappedBy = "passenger", cascade = CascadeType.ALL)
    private Ticket ticket;
}
