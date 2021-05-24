package com.sprint3.backend.services;

import com.sprint3.backend.model.StudentDTO;

import java.util.List;

public interface StudentService {

    /*List student*/
    List<StudentDTO> getAllStudent(Long id);
}
