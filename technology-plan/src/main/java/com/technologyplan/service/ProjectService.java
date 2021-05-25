package com.technologyplan.service;

import com.technologyplan.dto.Project.ProjectDTO;
import com.technologyplan.dto.Project.ProjectInfoDTO;
import com.technologyplan.dto.SearchVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {

    ProjectDTO findProjectById(Long id) throws Exception;

    Page<ProjectDTO> getAllProjects(SearchVO search, Pageable pageable) throws Exception;

    List<ProjectDTO> getAllProjects();

    void saveProject(ProjectInfoDTO projectDTO) throws Exception;

    void deleteProject(Long id) throws Exception;

}
