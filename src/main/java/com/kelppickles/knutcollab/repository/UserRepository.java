package com.kelppickles.knutcollab.repository;

import com.kelppickles.knutcollab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
