package com.sprint3.backend.services.impl;

import com.sprint3.backend.model.StudentGroupDTO;
import com.sprint3.backend.repository.StudentGroupRepository;
import com.sprint3.backend.services.StudentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {

    @Autowired
    private StudentGroupRepository studentGroupRepository;

    /*List student group*/
    @Override
    public List<StudentGroupDTO> findAllStudentGroup(Long id) {
        return this.studentGroupRepository.findAllStudentGroup(id);
    }
}
