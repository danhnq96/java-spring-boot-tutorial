package com.sprint1backend.service.flight_information;

import com.sprint1backend.entity.FlightInformation;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FlightInformationService {
    FlightInformation findFlightInformationByID(Long id);

    Page<FlightInformation> findAllByDepartureAndArrivalAndDepartureDateAfter(int page, String departure,
                                                                              String arrival, String departureDate,
                                                                              String sort);
    List<FlightInformation> findAllFlightInformation();
}
