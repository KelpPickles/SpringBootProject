package com.kelppickles.knutcollab.repository;

import com.kelppickles.knutcollab.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
