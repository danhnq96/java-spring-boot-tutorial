package com.technologyplan.controller;

import com.technologyplan.dto.Project.ProjectDTO;
import com.technologyplan.dto.SearchVO;
import com.technologyplan.dto.Technology.TechnologyDTO;
import com.technologyplan.dto.TechnologyPlanContentDTO;
import com.technologyplan.service.ProjectService;
import com.technologyplan.service.TechnologyPlanContentService;
import com.technologyplan.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TechnologyService technologyService;

    @Autowired
    private TechnologyPlanContentService technologyPlanContentService;

    @GetMapping(value = "/list-project")
    public String getListProjects(@ModelAttribute("search") SearchVO search,
                                  Model model,
                                  Pageable pageable) throws Exception {
        pageable = PageRequest.of(search.getPage() <= 0 ? 0 : pageable.getPageNumber(), pageable.getPageSize());

        Page<ProjectDTO> projectList = this.projectService.getAllProjects(search, pageable);

        model.addAttribute("projectList", projectList);
        model.addAttribute("pageNumber", 0);
        model.addAttribute("totalCount", projectList.getTotalElements());
        return "project/list-project";

    }

    @GetMapping(value = "/add-project")
    public String getFormAddProject() {
        return "project/add-project";
    }

    @PostMapping(value = "/delete-project")
    public String deleteProject(@RequestParam(name = "idProject") String idProject) throws Exception {
        this.projectService.deleteProject(Long.parseLong(idProject));
        return "redirect:/projects/list-project";
    }

    @GetMapping(value = "/update-project/{id}")
    public String getFormUpdateProject(Model model, @PathVariable(name = "id") Long id) throws Exception {
        ProjectDTO projectDTO = this.projectService.findProjectById(id);
        model.addAttribute("project", projectDTO);

        List<TechnologyDTO> technologyDTOList = this.technologyService.getAllTechnologyByProjectId(id);
        model.addAttribute("technologyList", technologyDTOList);

        return "project/update-project";
    }
}
