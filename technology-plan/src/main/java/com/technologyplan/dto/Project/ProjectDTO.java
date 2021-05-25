package com.technologyplan.dto.Project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private Long id;
    private String nameProject;
    private String content;
    private String manager;
    private String structure;
    private String image;
    private Date deletedAt;

}
