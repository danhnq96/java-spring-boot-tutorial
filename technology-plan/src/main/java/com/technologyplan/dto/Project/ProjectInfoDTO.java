package com.technologyplan.dto.Project;

import com.technologyplan.dto.Technology.TechnologyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInfoDTO {

    private ProjectDTO project;
    private List<TechnologyDTO> technologyList;
    private Date deletedAt;

}
