package com.sprint3.backend.services.impl;

import com.sprint3.backend.repository.TeacherRepository;
import com.sprint3.backend.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    public TeacherRepository teacherRepository;

    /*Get id teacher*/
    @Override
    public Long getIdTeacher(Long id) {
        return this.teacherRepository.getIdTeacher(id);
    }


}
