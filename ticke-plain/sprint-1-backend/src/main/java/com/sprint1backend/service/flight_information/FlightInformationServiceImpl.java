package com.sprint1backend.service.flight_information;

import com.sprint1backend.entity.FlightInformation;
import com.sprint1backend.repository.FlightInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FlightInformationServiceImpl implements FlightInformationService {
    @Autowired
    private FlightInformationRepository flightInformationRepository;

    @Override
    public FlightInformation findFlightInformationByID(Long id) {
        return this.flightInformationRepository.findById(id).orElse(null);
    }

    @Override
    public Page<FlightInformation> findAllByDepartureAndArrivalAndDepartureDateAfter(int page, String departure,
                                                                                     String arrival, String departureDate,
                                                                                     String sort) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
        LocalDate localDate = LocalDate.parse(departureDate, formatter);
        Sort sort1 = Sort.by(sort).ascending();
        Pageable pageable1 = PageRequest.of(page, 5, sort1);
        return this.flightInformationRepository.getAllByDepartureAndArrivalAndDepartureDate(departure, arrival,
                localDate, pageable1);
    }

    @Override
    public List<FlightInformation> findAllFlightInformation() {
        return this.flightInformationRepository.findAll();
    }
}
