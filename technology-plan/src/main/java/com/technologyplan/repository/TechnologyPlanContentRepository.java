package com.technologyplan.repository;

import com.technologyplan.entity.TechnologyPlanContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnologyPlanContentRepository extends JpaRepository<TechnologyPlanContent, Long> {

//    @Query("SELECT tecPC.content FROM TechnologyPlanContent tecPC " +
//            " INNER JOIN Technology tec ON tecPC.technology.id = tec.id WHERE tec.id =:id")
//    List<TechnologyPlanContent> getAllTechnologyPlanContentByIdTechnology(@Param("id") Long id);
}
