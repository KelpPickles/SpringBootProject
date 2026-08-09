package com.kelppickles.knutcollab.controller;

import com.kelppickles.knutcollab.response.ApiResponse;
import com.kelppickles.knutcollab.dto.ProjectCreateRequest;
import com.kelppickles.knutcollab.dto.ProjectUpdateRequest;
import com.kelppickles.knutcollab.entity.Project;
import com.kelppickles.knutcollab.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/projects")
    public ApiResponse<Long> createProject(@Valid @RequestBody ProjectCreateRequest request) {
        return new ApiResponse<>(true, projectService.createProject(request));
    }

    @GetMapping("/projects")
    public ApiResponse<Page<Project>> getProjects(@RequestParam(required = false) String keyword,
                                    Pageable pageable) {
        return new ApiResponse<>(true,
                projectService.getProjects(keyword, pageable));
    }

    @GetMapping("/project/{id}")
    public ApiResponse<Project> getProject(@PathVariable("id") Long id) {
        return new ApiResponse<>(true,
                projectService.getProject(id));
    }

    @PutMapping("/project/{id}")
    public ApiResponse<Long> updateProject(@PathVariable("id") Long id,
                              @RequestBody ProjectUpdateRequest request) {
        return new ApiResponse<>(true, projectService.updateProject(id, request));
    }

    @DeleteMapping("/project/{id}")
    public ApiResponse<Long> deleteProject(@PathVariable("id") Long id) {
        return new ApiResponse<>(true, projectService.deleteProject(id));
    }
}
