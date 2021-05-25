package com.technologyplan.repository;

import com.technologyplan.dto.SearchVO;
import com.technologyplan.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    @Query(value = "SELECT pro FROM Project pro" +
            " WHERE ((:#{#search.keyword} IS NULL OR pro.nameProject LIKE %:#{#search.keyword}%)" +
            " OR (:#{#search.keyword} IS NULL OR pro.content LIKE %:#{#search.keyword}%)" +
            " OR (:#{#search.keyword} IS NULL OR pro.manager LIKE %:#{#search.keyword}%))" +
            " OR (:#{#search.keyword} IS NULL OR pro.structure LIKE %:#{#search.keyword}%)" +
            " ORDER BY pro.nameProject ASC")
    Page<Project> getAllProjectList(@Param("search") SearchVO search, Pageable pageable);



    @Query(value = "SELECT pro FROM Project pro WHERE pro.nameProject =:nameProject ")
    Project getProjectByName(@Param("nameProject") String nameProject);

}
