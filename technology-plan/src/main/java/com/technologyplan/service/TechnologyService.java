package com.technologyplan.service;

import com.technologyplan.dto.Project.ProjectInfoDTO;
import com.technologyplan.dto.Technology.TechnologyDTO;
import com.technologyplan.dto.Technology.TechnologyInfoDTO;

import java.util.List;

public interface TechnologyService {

    List<TechnologyDTO> getAllTechnologyByProjectId(Long id);

    List<TechnologyInfoDTO> getAllTechnologyInfoByProjectId(Long id);

    void saveTechnology(ProjectInfoDTO projectDTO) throws Exception;

}
