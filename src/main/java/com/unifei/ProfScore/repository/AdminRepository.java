package com.unifei.ProfScore.repository;

import com.unifei.ProfScore.domain.Administrator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AdminRepository extends JpaRepository<Administrator, Integer> {

    Optional<Administrator> findByEmail(String email);
}
