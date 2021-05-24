package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.ProgressThesis;

@Repository
public interface ProgressThesisRepository extends JpaRepository<ProgressThesis, Long> {
}
