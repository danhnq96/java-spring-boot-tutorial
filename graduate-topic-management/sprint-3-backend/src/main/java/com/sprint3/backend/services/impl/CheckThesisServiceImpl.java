package com.sprint3.backend.services.impl;

import com.sprint3.backend.entity.CheckThesis;
import com.sprint3.backend.model.CheckThesisDTO;
import com.sprint3.backend.repository.CheckThesisRepository;
import com.sprint3.backend.services.CheckThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckThesisServiceImpl implements CheckThesisService {

    @Autowired
    private CheckThesisRepository checkThesisRepository;

    /*List check thesis unapproved*/
    @Override
    public List<CheckThesisDTO> getAllCheckThesis(Long idTeacherParam) {
        return this.checkThesisRepository.getAllCheckThesis(idTeacherParam);
    }

    /*List check thesis approved*/
    @Override
    public List<CheckThesisDTO> getAllCheckThesisApproved(Long idTeacher) {
        return this.checkThesisRepository.getAllCheckThesisApproved(idTeacher);
    }


    /*Save check thesis*/
    @Override
    public void saveCheckThesis(LocalDateTime checkDate, Long idCheckThesis) {
        this.checkThesisRepository.saveCheckThesis(checkDate, idCheckThesis);
    }

    /*Display list delete thesis unapproved*/
    @Override
    public List<CheckThesis> loadListDeleteCheckThesis(Long idTeacherParam) {
        return this.checkThesisRepository.loadListDeleteCheckThesis(idTeacherParam);
    }

    /*Update check thesis*/
    @Override
    public void saveUpdateCheckThesis(Long idParam) {
        this.checkThesisRepository.saveUpdateCheckThesis(idParam);
    }

    /*Delete list check thesis unapproved*/
    @Override
    public void deleteCheckThesisUnApproved(Long idCheckThesis) {
        this.checkThesisRepository.deleteById(idCheckThesis);
    }

}
