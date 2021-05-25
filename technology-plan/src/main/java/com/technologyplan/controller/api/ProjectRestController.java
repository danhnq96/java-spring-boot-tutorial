package com.technologyplan.controller.api;

import com.technologyplan.dto.Project.ProjectInfoDTO;
import com.technologyplan.service.ProjectService;
import com.technologyplan.service.ProjectTechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectRestController {

    @Autowired
    private ProjectTechnologyService projectTechnologyService;

    @PostMapping(value = "/add-project")
    public ResponseEntity<String> addProject(@RequestBody ProjectInfoDTO projectInfoDTO) throws Exception {
        this.projectTechnologyService.saveProjectTechnology(projectInfoDTO);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

//    @PostMapping(value = "/add-project")
//    public ResponseEntity<String> addProject(@RequestBody ProjectInfoDTO projectInfoDTO,
//                                             @RequestPart(value = "thumbnail") MultipartFile file) throws Exception {
//        this.projectTechnologyService.saveProjectTechnology(projectInfoDTO);
//        System.out.println(projectInfoDTO);
//        System.out.println(file);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }

    @PostMapping(value = "/update-project")
    public ResponseEntity<String> updateProject(@RequestBody ProjectInfoDTO projectInfoDTO) throws Exception {
        this.projectTechnologyService.saveProjectTechnology(projectInfoDTO);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
