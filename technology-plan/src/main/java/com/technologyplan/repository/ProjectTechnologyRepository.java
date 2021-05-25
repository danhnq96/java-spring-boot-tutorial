package com.technologyplan.repository;

import com.technologyplan.entity.ProjectTechnology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTechnologyRepository extends JpaRepository<ProjectTechnology, Long> {
}
