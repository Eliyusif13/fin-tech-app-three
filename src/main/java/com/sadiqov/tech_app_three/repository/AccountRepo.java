package com.sadiqov.tech_app_three.repository;

import com.sadiqov.tech_app_three.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNo(Integer accountNo);
}
