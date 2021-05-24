package com.sprint3.backend.services;

import com.sprint3.backend.model.StudentGroupDTO;

import java.util.List;

public interface StudentGroupService {

    /*List student group*/
    List<StudentGroupDTO> findAllStudentGroup(Long id);
}
