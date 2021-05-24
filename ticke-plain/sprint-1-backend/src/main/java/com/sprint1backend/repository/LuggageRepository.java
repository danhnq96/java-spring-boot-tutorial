package com.sprint1backend.repository;

import com.sprint1backend.entity.Luggage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LuggageRepository extends JpaRepository<Luggage, Long> {
}
