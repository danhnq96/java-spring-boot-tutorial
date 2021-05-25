package com.technologyplan.service.impl;

import com.technologyplan.dto.Project.ProjectInfoDTO;
import com.technologyplan.dto.Technology.TechnologyDTO;
import com.technologyplan.dto.Technology.TechnologyInfoDTO;
import com.technologyplan.entity.Technology;
import com.technologyplan.repository.TechnologyRepository;
import com.technologyplan.service.TechnologyService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    public static ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<TechnologyDTO> getAllTechnologyByProjectId(Long id) {
        List<Technology> technologyList = this.technologyRepository.getAllTechnologyByProjectId(id);

        Type typeList = new TypeToken<List<TechnologyDTO>>() {
        }.getType();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<TechnologyDTO> technologyDTOList = modelMapper.map(technologyList, typeList);
        return technologyDTOList;

    }

    @Override
    public List<TechnologyInfoDTO> getAllTechnologyInfoByProjectId(Long id) {

        List<Technology> technologyList = this.technologyRepository.getAllTechnologyByProjectId(id);

        Type typeList = new TypeToken<List<TechnologyInfoDTO>>() {
        }.getType();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<TechnologyInfoDTO> technologyInfoList = modelMapper.map(technologyList, typeList);
        return technologyInfoList;
    }

    @Override
    public void saveTechnology(ProjectInfoDTO projectDTO) throws Exception {
//        Technology technology;
//
//        List<TechnologyDTO> technologyDTOList = projectDTO.getTechnologyList();
//
//        for (TechnologyDTO technologyDTO : technologyDTOList) {
//
//            if (technologyDTO.getId() == null) {
//                technology = new Technology();
//            } else {
//                technology = this.technologyRepository.findById(Long.parseLong(technologyDTO.getId())).orElse(null);
//            }
//            if (technology != null) {
//                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//                modelMapper.map(technologyDTO, technology);
//                this.technologyRepository.save(technology);
//            }
//        }
    }

}
