package com.sadiqov.tech_app_three.repository;

import com.sadiqov.tech_app_three.entity.TechUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<TechUser, Long> {

    @Query(value="select p from TechUser p join fetch p.accountList where p.pin =:p")
    Optional<TechUser> findBYPin(String pin);
}
