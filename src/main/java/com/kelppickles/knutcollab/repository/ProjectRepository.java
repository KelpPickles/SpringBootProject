package com.kelppickles.knutcollab.repository;

import com.kelppickles.knutcollab.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByTitleContaining(String keyword, Pageable pageable);

}
