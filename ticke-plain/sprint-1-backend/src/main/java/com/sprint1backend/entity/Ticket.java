package com.sprint1backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "ticket_code", columnDefinition = "VARCHAR(40)")
    private String ticketCode;

    @Column(name = "passenger_name", columnDefinition = "VARCHAR(50)")
    private String passengerName;

    @Column(name = "price_departure", columnDefinition = "Double")
    private Double priceDeparture;

    @Column(name = "price_arrival", columnDefinition = "Double")
    private Double priceArrival;

    @Column(name = "status_checkin", columnDefinition = "BIT")
    private Boolean statusCheckin;

    @ManyToOne
    @JoinColumn(name = "flight_information_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private FlightInformation flightInformation;

//
    @ManyToOne
    @JoinColumn(name = "status_payment_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private StatusPayment statusPayment;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "app_user_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private AppUser appUser;

    @OneToOne
    @JoinColumn(name = "passenger_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    @JsonBackReference
    private Passenger passenger;

    public void setStatusCheckin(Boolean statusCheckin) {
        this.statusCheckin = statusCheckin;
    }
}
