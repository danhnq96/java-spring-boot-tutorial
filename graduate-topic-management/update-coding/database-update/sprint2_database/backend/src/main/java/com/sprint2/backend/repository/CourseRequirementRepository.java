package com.sprint2.backend.repository;

import com.sprint2.backend.entity.CourseRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRequirementRepository extends JpaRepository<CourseRequirement, Long> {
}
