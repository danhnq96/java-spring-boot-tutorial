package com.sprint1backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Long id;
    private Long adults;
    private String appUser;
    private Long babies;
    private String booking;
    private Long employee;
    private Long invoice;
    private String passengerName;
    private Double priceArrival;
    private Double priceDeparture;
    private Boolean statusCheckin;
    private String statusPayment;
    private String ticketCode;
    private Long flightInformation;
}
