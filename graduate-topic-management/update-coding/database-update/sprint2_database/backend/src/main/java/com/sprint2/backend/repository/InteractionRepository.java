package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.Interaction;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
}
