package com.technologyplan.controller;

import com.technologyplan.dto.Project.ProjectDTO;
import com.technologyplan.dto.Technology.TechnologyDTO;
import com.technologyplan.service.HashtagService;
import com.technologyplan.service.ProjectService;
import com.technologyplan.service.TechnologyService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectWebController {

    public static final Log log = LogFactory.getLog(ProjectWebController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TechnologyService technologyService;

    @Autowired
    private HashtagService hashtagService;

    @GetMapping(value = "/detail/{id}")
    public String getAllProjects(Model model, @PathVariable(value = "id") Long id) throws Exception {
        ProjectDTO project = this.projectService.findProjectById(id);
        model.addAttribute("project", project);

        List<TechnologyDTO> technologyList = this.technologyService.getAllTechnologyByProjectId(id);
        model.addAttribute("technologyList", technologyList);

        List<ProjectDTO> projectList = this.projectService.getAllProjects();
        model.addAttribute("projectList", projectList);

        List<String> hashtagNameList = this.hashtagService.getAllNameHashtagByTechnologyId(id);
        model.addAttribute("hashtagNameList", hashtagNameList);

        log.info("HIEN THI PROJECT THANH CONG");
        return "project/overview-technology-plan";

    }

}
