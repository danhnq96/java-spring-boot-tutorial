package com.technologyplan.service.impl;

import com.technologyplan.dto.Project.ProjectDTO;
import com.technologyplan.dto.Project.ProjectInfoDTO;
import com.technologyplan.dto.SearchVO;
import com.technologyplan.entity.Project;
import com.technologyplan.repository.ProjectRepository;
import com.technologyplan.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public static ModelMapper modelMapper = new ModelMapper();

    @Override
    public ProjectDTO findProjectById(Long id) throws Exception {
        try {
            Project project = this.projectRepository.findById(id).orElse(null);
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            return modelMapper.map(project, ProjectDTO.class);
        } catch (Exception exception) {
            throw new Exception("CAN NOT FIND PROJECT ID = " + id);
        }

    }


    @Override
    public Page<ProjectDTO> getAllProjects(SearchVO search, Pageable pageable) throws Exception {
        try {
//            Specification<Project> projectSpec = Specification.where(findAllProject(search));
//            Page<Project> projectPage = this.projectRepository.findAll(projectSpec, pageable);

            Page<Project> projectPage = this.projectRepository.getAllProjectList(search, pageable);
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            return projectPage.map(project -> modelMapper.map(project, ProjectDTO.class));
        } catch (Exception exception) {
            throw new Exception("CAN NOT LOAD LIST PROJECTS");
        }

    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projectList = this.projectRepository.findAll();
        Type typeList = new TypeToken<List<ProjectDTO>>() {
        }.getType();
        List<ProjectDTO> projectDTOList = modelMapper.map(projectList, typeList);
        return projectDTOList;
    }

    @Override
    public void saveProject(ProjectInfoDTO projectDTO) throws Exception {
//        Project project;
//        if (projectDTO.getProject().getId() == null) {
//            project = new Project();
//        } else {
//            project = this.projectRepository.findById(Long.parseLong(projectDTO.getProject().getId().toString())).orElse(null);
//        }
//        if (project != null) {
//            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//            modelMapper.map(projectDTO.getProject(), project);
//            this.projectRepository.save(project);
//
//        } else {
//            throw new Exception("CAN NOT SAVE PROJECT");
//        }
    }

    @Override
    public void deleteProject(Long id) throws Exception {
        Project project = this.projectRepository.findById(id).orElse(null);
        if (project != null) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            ProjectDTO projectDTO = new ProjectDTO();
            modelMapper.map(project, projectDTO);
            projectDTO.setDeletedAt(new Date());
            modelMapper.map(projectDTO, project);
            this.projectRepository.save(project);
        } else {
            throw new Exception("CAN NOT DELETE PROJECT");
        }
    }
}
