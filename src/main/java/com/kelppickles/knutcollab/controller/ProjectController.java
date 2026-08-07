package com.kelppickles.knutcollab.controller;

import com.kelppickles.knutcollab.dto.ProjectCreateRequest;
import com.kelppickles.knutcollab.dto.ProjectUpdateRequest;
import com.kelppickles.knutcollab.entity.Project;
import com.kelppickles.knutcollab.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/projects")
    public void createProject(@RequestBody ProjectCreateRequest request) {
        projectService.createProject(request);
    }

    @GetMapping("/projects")
    public List<Project> getProject() {
        return projectService.getProjects();
    }

    @GetMapping("/projects/{id}")
    public Project getProject(@PathVariable("id") Long id) {
        return projectService.getProject(id);
    }

    @PutMapping("/projects/{id}")
    public void updateProject(@PathVariable("id") Long id,
                              @RequestBody ProjectUpdateRequest request) {
        projectService.updateProject(id, request);
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
    }
}
