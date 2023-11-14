package com.example.task.controller;

import com.example.task.model.Project;
import com.example.task.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/project/{id}")
    public ResponseEntity<Project> createProject(@PathVariable(value="id") String id,@RequestBody Project project) {
        Project createdProject = projectService.createProject(id,project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }


}