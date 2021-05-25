package com.technologyplan.repository;

import com.technologyplan.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long>, JpaSpecificationExecutor<Hashtag> {

    @Query("SELECT hash.hashtagName FROM Hashtag hash INNER JOIN Technology tec WHERE tec.id = :id")
    List<Hashtag> getAllHashtagByTechnologyId(@Param("id") Long id);

}
