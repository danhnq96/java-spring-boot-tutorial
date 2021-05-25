package com.technologyplan.repository;

import com.technologyplan.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long>, JpaSpecificationExecutor<Technology> {

    @Query("SELECT tec FROM Technology tec INNER JOIN ProjectTechnology proTec ON tec.id = proTec.technologyId " +
            " INNER JOIN Project pro ON proTec.projectId = pro.id WHERE pro.id = :id")
    List<Technology> getAllTechnologyByProjectId(@Param("id") Long id);

//    Technology getTechnologyByTechnologyName(String nameTechnology);
}
