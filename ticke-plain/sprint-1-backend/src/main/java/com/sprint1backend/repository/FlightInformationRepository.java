package com.sprint1backend.repository;

import com.sprint1backend.entity.FlightInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FlightInformationRepository extends JpaRepository<FlightInformation, Long> {
@Query(value="select * from flight_information where `departure`=?1 and `arrival` = ?2 and `departure_date` = ?3", nativeQuery = true)
    Page<FlightInformation> getAllByDepartureAndArrivalAndDepartureDate(
        String departure, String arrival, LocalDate departureDate, Pageable pageable);
}
