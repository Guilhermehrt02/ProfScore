package com.unifei.ProfScore.repository;

import com.unifei.ProfScore.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    Optional<Professor> findByName(String name);
}
