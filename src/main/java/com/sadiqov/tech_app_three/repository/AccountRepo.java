package com.sadiqov.tech_app_three.repository;

import com.sadiqov.tech_app_three.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
}
