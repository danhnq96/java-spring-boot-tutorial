package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.StudentGroup;

@Repository
public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {
}
