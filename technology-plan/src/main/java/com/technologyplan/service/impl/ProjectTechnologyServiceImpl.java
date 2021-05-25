package com.technologyplan.service.impl;

import com.technologyplan.converter.ConvertProjectTechnology;
import com.technologyplan.dto.Project.ProjectInfoDTO;
import com.technologyplan.dto.ProjectTechnologyDTO;
import com.technologyplan.dto.Technology.TechnologyDTO;
import com.technologyplan.dto.TechnologyPlanContentDTO;
import com.technologyplan.entity.Project;
import com.technologyplan.entity.Technology;
import com.technologyplan.entity.TechnologyPlanContent;
import com.technologyplan.repository.ProjectRepository;
import com.technologyplan.repository.ProjectTechnologyRepository;
import com.technologyplan.repository.TechnologyPlanContentRepository;
import com.technologyplan.repository.TechnologyRepository;
import com.technologyplan.service.ProjectTechnologyService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProjectTechnologyServiceImpl implements ProjectTechnologyService {

    @Autowired
    private ProjectTechnologyRepository projectTechnologyRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TechnologyRepository technologyRepository;
    @Autowired
    private TechnologyPlanContentRepository technologyPlanContentRepository;

    public static ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public void saveProjectTechnology(ProjectInfoDTO projectInfoDTO) throws Exception {

        // add project
        Project project;

        if (projectInfoDTO.getProject().getId() == null) {
            project = new Project();
        } else {
            project = this.projectRepository.findById(projectInfoDTO.getProject().getId()).orElse(null);
        }
        if (project != null) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(projectInfoDTO.getProject(), project);
            this.projectRepository.save(project);

        } else {
            throw new Exception("CAN NOT SAVE PROJECT");
        }

        // add technology list
        Technology technology;

        ProjectTechnologyDTO projectTechnologyDTO = new ProjectTechnologyDTO();

        // get list technology dto
        List<TechnologyDTO> technologyDTOList = projectInfoDTO.getTechnologyList();

        // save technology
        for (TechnologyDTO technologyDTO : technologyDTOList) {

            if (technologyDTO.getId() == null) {
                technology = new Technology();
            } else {
                technology = this.technologyRepository.findById(technologyDTO.getId()).orElse(null);
            }
            technology.setId(technologyDTO.getId());
            technology.setTechnologyName(technologyDTO.getTechnologyName());
            this.technologyRepository.save(technology);


            // save project - technology
            if (technologyDTO.getId() == null) {
                projectTechnologyDTO.setProjectId(project.getId());
                projectTechnologyDTO.setTechnologyId(technology.getId());
                this.projectTechnologyRepository.save(ConvertProjectTechnology.DTOToDb(projectTechnologyDTO));
            }

            TechnologyPlanContent technologyPlanContent;

            // get list technology plan dto
            List<TechnologyPlanContentDTO> technologyPlanContentDTOList = technologyDTO.getTechnologyPlanContentList();

            // save technology plan
            for (TechnologyPlanContentDTO planContentDTO : technologyPlanContentDTOList) {
                if (planContentDTO.getId() == null) {
                    technologyPlanContent = new TechnologyPlanContent();
                } else {
                    technologyPlanContent = this.technologyPlanContentRepository.findById(planContentDTO.getId()).orElse(null);
                }
                technologyPlanContent.setId(planContentDTO.getId());
                technologyPlanContent.setContent(planContentDTO.getContent());
                technologyPlanContent.setTechnology(technology);
                this.technologyPlanContentRepository.save(technologyPlanContent);

            }
        }
    }
}
