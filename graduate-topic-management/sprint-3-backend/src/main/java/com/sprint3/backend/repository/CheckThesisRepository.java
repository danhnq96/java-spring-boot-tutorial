package com.sprint3.backend.repository;

import com.sprint3.backend.entity.CheckThesis;
import com.sprint3.backend.model.CheckThesisDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CheckThesisRepository extends JpaRepository<CheckThesis, Long> {

    /*List check thesis unapproved*/
    @Query(nativeQuery = true, value = "select * from project3_thesis_management.check_list_thesis\n" +
            "where project3_thesis_management.check_list_thesis.idTeacher = :idTeacherParam\n" +
            "and project3_thesis_management.check_list_thesis.status = false;")
    List<CheckThesisDTO> getAllCheckThesis(Long idTeacherParam);

    /*List check thesis approved*/
    @Query(nativeQuery = true, value = "select * from project3_thesis_management.check_list_thesis\n" +
            "where project3_thesis_management.check_list_thesis.idTeacher = :idTeacherParam\n" +
            "and project3_thesis_management.check_list_thesis.status = true;")
    List<CheckThesisDTO> getAllCheckThesisApproved(Long idTeacherParam);

    /*Save check thesis*/
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update check_thesis set status = true, check_date = :checkDate where id = :idCheckThesis")
    void saveCheckThesis(LocalDateTime checkDate, Long idCheckThesis);

    /*Display list delete thesis unapproved*/
    @Query(nativeQuery = true, value = "select * from project3_thesis_management.check_thesis\n" +
            "         inner join project3_thesis_management.thesis on check_thesis.thesis_id = thesis.id\n" +
            "         inner join project3_thesis_management.teacher on thesis.teacher_id = teacher.id\n" +
            "where status = false and teacher.id = :idTeacherParam")
    List<CheckThesis> loadListDeleteCheckThesis(Long idTeacherParam);

    /*Update check thesis*/
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update check_thesis set student_group_id = null, thesis_id =null where id = :idParam")
    void saveUpdateCheckThesis(Long idParam);

}
