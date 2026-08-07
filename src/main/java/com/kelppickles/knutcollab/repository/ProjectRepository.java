package com.kelppickles.knutcollab.repository;

import com.kelppickles.knutcollab.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
