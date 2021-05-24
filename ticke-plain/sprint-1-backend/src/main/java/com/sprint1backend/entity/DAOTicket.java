package com.sprint1backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DAOTicket {
    private String[] ticketCode;
    private String[] passengerName;
    private Double[] priceDeparture;
    private Double[] priceArrival;
    private List<Long> flightInformation;
    private DAOBooking daoBooking;
    private Integer countDeparture;
    private Integer countArrival;
}
