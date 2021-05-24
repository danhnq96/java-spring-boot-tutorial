package com.sprint3.backend.controllers;

import com.sprint3.backend.model.StudentDTO;
import com.sprint3.backend.model.StudentGroupDTO;
import com.sprint3.backend.services.StudentGroupService;
import com.sprint3.backend.services.StudentService;
import com.sprint3.backend.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/group")
@CrossOrigin
public class GroupController {

    @Autowired
    private StudentGroupService studentGroupService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    /*List student*/
    @RequestMapping(value = "/list-student-group/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<StudentDTO>> getAllStudentGroup(@PathVariable Long id) {
        Long idTeacher = this.teacherService.getIdTeacher(id);
        List<StudentGroupDTO> studentGroupList = this.studentGroupService.findAllStudentGroup(idTeacher);
        List<StudentDTO> students = new ArrayList<>();
        for (StudentGroupDTO studentGroupDTO : studentGroupList) {
            students.addAll(this.studentService.getAllStudent(studentGroupDTO.getIdStudentGroup()));
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
