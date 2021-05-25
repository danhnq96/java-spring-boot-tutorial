package com.technologyplan.converter;

import com.technologyplan.dto.ProjectTechnologyDTO;
import com.technologyplan.entity.ProjectTechnology;

public class ConvertProjectTechnology {

    public static ProjectTechnology DTOToDb(ProjectTechnologyDTO projectTechnologyDTO) {
        if (projectTechnologyDTO == null) {
            return null;
        }
        ProjectTechnology projectTechnology = new ProjectTechnology();
        projectTechnology.setProjectId(projectTechnologyDTO.getProjectId());
        projectTechnology.setTechnologyId(projectTechnologyDTO.getTechnologyId());

        return projectTechnology;
    }

    public static ProjectTechnologyDTO dbToDTO(ProjectTechnology projectTechnology) {
        if (projectTechnology == null) {
            return null;
        }

        ProjectTechnologyDTO projectTechnologyDTO = new ProjectTechnologyDTO();
        projectTechnologyDTO.setProjectId(projectTechnology.getProjectId());
        projectTechnologyDTO.setTechnologyId(projectTechnology.getTechnologyId());
        return projectTechnologyDTO;
    }

}
