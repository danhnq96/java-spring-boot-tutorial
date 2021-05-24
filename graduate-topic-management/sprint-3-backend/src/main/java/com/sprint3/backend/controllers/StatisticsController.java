package com.sprint3.backend.controllers;

import com.sprint3.backend.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@CrossOrigin
public class StatisticsController {
    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping("/teacher")
    public ResponseEntity<?> teacher() {
        return ResponseEntity.ok(teacherRepository.findAll());
    }
}
