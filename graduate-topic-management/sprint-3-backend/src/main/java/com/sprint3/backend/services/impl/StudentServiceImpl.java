package com.sprint3.backend.services.impl;

import com.sprint3.backend.model.StudentDTO;
import com.sprint3.backend.repository.StudentRepository;
import com.sprint3.backend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    /*List student*/
    @Override
    public List<StudentDTO> getAllStudent(Long id) {
        return this.studentRepository.getAllStudent(id);
    }
}
