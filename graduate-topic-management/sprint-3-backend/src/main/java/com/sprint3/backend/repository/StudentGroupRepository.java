package com.sprint3.backend.repository;

import com.sprint3.backend.entity.StudentGroup;
import com.sprint3.backend.model.StudentGroupDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {

    /*List student group*/
    @Query(nativeQuery = true, value = "select student_group.id as idStudentGroup\n" +
            "from student_group\n" +
            "inner join teacher on student_group.teacher_id = teacher.id\n" +
            "where teacher.id = :idParam")
    List<StudentGroupDTO> findAllStudentGroup(Long idParam);
}
