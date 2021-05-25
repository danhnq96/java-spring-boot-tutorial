package com.technologyplan.service.impl;

import com.technologyplan.dto.Technology.TechnologyDTO;
import com.technologyplan.dto.TechnologyPlanContentDTO;
import com.technologyplan.entity.TechnologyPlanContent;
import com.technologyplan.repository.TechnologyPlanContentRepository;
import com.technologyplan.service.TechnologyPlanContentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyPlanContentServiceImpl implements TechnologyPlanContentService {

    @Autowired
    private TechnologyPlanContentRepository planContentRepo;

    public static ModelMapper modelMapper = new ModelMapper();

    @Override
    public void saveTechnologyPlanContent(TechnologyPlanContentDTO technologyPlanContentDTO) {
        TechnologyPlanContent technologyPlanContent;
        if (technologyPlanContentDTO.getId() == null) {
            technologyPlanContent = new TechnologyPlanContent();
        } else {
            technologyPlanContent = this.planContentRepo.findById(technologyPlanContentDTO.getId()).orElse(null);
        }
        if (technologyPlanContent != null) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(technologyPlanContentDTO, technologyPlanContent);
            this.planContentRepo.save(technologyPlanContent);
        }
    }

}
