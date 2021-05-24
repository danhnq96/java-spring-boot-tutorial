package com.sprint1backend.controller;

import com.sprint1backend.entity.FlightInformation;
import com.sprint1backend.service.flight_information.FlightInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight-information")
@CrossOrigin
public class FlightInformationRestController {
    @Autowired
    private FlightInformationService flightInformationService;

    @GetMapping("/list")
    public ResponseEntity<List<FlightInformation>> getListFlightInformation() {
        List<FlightInformation> flightInformationList = this.flightInformationService.findAllFlightInformation();
        return new ResponseEntity<>(flightInformationList, HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<FlightInformation> getFlightInformationByID(@PathVariable Long id) {
        FlightInformation flightInformation = this.flightInformationService.findFlightInformationByID(id);
        return new ResponseEntity<>(flightInformation, HttpStatus.OK);
    }

    // Đin Start task
    @GetMapping("/search")
    public ResponseEntity<Page<FlightInformation>> getFlightInformation(@RequestParam int page,
                                                                        @RequestParam String departure,
                                                                        @RequestParam String arrival,
                                                                        @RequestParam String departureDate,
                                                                        @RequestParam String sort) {
        System.out.println(this.flightInformationService.findAllByDepartureAndArrivalAndDepartureDateAfter(
                page , departure , arrival, departureDate, sort).toString());
        return new ResponseEntity<>(this.flightInformationService.findAllByDepartureAndArrivalAndDepartureDateAfter(
                page , departure , arrival, departureDate, sort),HttpStatus.OK);
    }
}
