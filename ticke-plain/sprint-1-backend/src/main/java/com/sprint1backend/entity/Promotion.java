package com.sprint1backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity(name = "promotion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "flight", columnDefinition = "VARCHAR(40)")
    private String flight;

    @Column(name = "airline", columnDefinition = "VARCHAR(40)")
    private String airline;

    @Column(name = "flight_number", columnDefinition = "VARCHAR(40)")
    private String flightNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "departure_date", columnDefinition = "DATE")
    private LocalDate departureDate;

    @Column(name = "departure_place", columnDefinition = "VARCHAR(40)")
    private String departurePlace;

    @Column(name = "departure_time", columnDefinition = "TIME")
    private LocalTime departureTime;

    @Column(name = "arrival_place", columnDefinition = "VARCHAR(40)")
    private String arrivalPlace;

    @Column(name = "arrival_time", columnDefinition = "TIME")
    private LocalTime arrivalTime;

    @Column(name = "information", columnDefinition = "VARCHAR(40)")
    private String information;

    @Column(name = "price", columnDefinition = "DOUBLE")
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalPlace() {
        return arrivalPlace;
    }

    public void setArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
