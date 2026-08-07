package com.kelppickles.knutcollab.service;

import com.kelppickles.knutcollab.dto.ProjectUpdateRequest;
import com.kelppickles.knutcollab.entity.Project;
import com.kelppickles.knutcollab.dto.ProjectCreateRequest;
import com.kelppickles.knutcollab.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public void createProject(ProjectCreateRequest request) {
        Project project = new Project(
                request.getTitle(),
                request.getDescription(),
                request.getMaxMember(),
                request.getStatus()
        );

        projectRepository.save(project);
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Project getProject(Long id) {
        return projectRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("프로젝트를 찾을 수 없습니다."));
    }

    public void updateProject(Long id, ProjectUpdateRequest request) {
        Project project = getProject(id);

        project.update(request.getTitle(),
                request.getDescription(),
                request.getMaxMember(),
                request.getStatus());

        projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = getProject(id);

        projectRepository.delete(project);
    }

}
