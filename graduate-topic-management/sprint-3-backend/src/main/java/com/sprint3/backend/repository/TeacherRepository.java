package com.sprint3.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint3.backend.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    /*Get id teacher*/
    @Query(nativeQuery = true, value = "select teacher.id from teacher where teacher.app_account_id = :idParam")
    Long getIdTeacher(Long idParam);
}
