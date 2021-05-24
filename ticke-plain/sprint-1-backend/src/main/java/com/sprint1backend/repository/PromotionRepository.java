package com.sprint1backend.repository;

import com.sprint1backend.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByFlightContaining (String string);
    List<Promotion> findByDepartureDate(LocalDate promotion);
//
}
