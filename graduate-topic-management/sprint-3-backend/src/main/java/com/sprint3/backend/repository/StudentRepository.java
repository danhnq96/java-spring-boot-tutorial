package com.sprint3.backend.repository;

import com.sprint3.backend.entity.Student;
import com.sprint3.backend.model.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /*List student*/
    @Query(nativeQuery = true, value = "select student.full_name as fullName, student_group.group_name as nameGroup,\n" +
            "       student.student_code as studentCode, student.email as email\n" +
            "from student\n" +
            "inner join student_group on student.group_id = student_group.id\n" +
            "where student.group_id = :idStudentParam")
    List<StudentDTO> getAllStudent(Long idStudentParam);
}