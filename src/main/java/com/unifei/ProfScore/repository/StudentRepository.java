package com.unifei.ProfScore.repository;

import com.unifei.ProfScore.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByEmail(String email);
}
