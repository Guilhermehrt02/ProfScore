package com.unifei.ProfScore.repository;

import com.unifei.ProfScore.model.University;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Integer> {
    Optional<University> findByName(String name);

    Optional<University> findByNameAndCity(@NotBlank(message = "University name is mandatory") String universityName, @NotBlank(message = "University city is mandatory") String universityCity);
}
