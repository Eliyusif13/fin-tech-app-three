package com.sadiqov.tech_app_three.repository;

import com.sadiqov.tech_app_three.entity.TechUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TechUser, Long> {
}
