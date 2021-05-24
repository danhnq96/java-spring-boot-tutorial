package com.sprint1backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity(name = "flight_information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "flight_code", columnDefinition = "VARCHAR(40)")
    private String flightCode;

    @Column(name = "type", columnDefinition = "VARCHAR(40)")
    private String type;

    @Column(name = "airline", columnDefinition = "VARCHAR(40)")
    private String airline;

    @Column(name = "departure", columnDefinition = "VARCHAR(40)")
    private String departure;

    @Column(name = "arrival", columnDefinition = "VARCHAR(40)")
    private String arrival;

    @Column(name = "departure_date", columnDefinition = "DATE")
    private LocalDate departureDate;

    @Column(name = "arrival_date", columnDefinition = "DATE")
    private LocalDate arrivalDate;

    @Column(name = "departure_time", columnDefinition = "TIME")
    private LocalTime departureTime;

    @Column(name = "arrival_time", columnDefinition = "TIME")
    private LocalTime arrivalTime;

    @Column(name = "price", columnDefinition = "DOUBLE")
    private Double price;

    @OneToMany(mappedBy = "flightInformation", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Ticket> ticketList;
}
