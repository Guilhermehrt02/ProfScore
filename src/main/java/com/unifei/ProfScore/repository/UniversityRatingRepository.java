package com.unifei.ProfScore.repository;

import com.unifei.ProfScore.model.UniversityRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversityRatingRepository extends JpaRepository<UniversityRating, Integer> {
    List<UniversityRating> findByUniversityId(int id);

    List<UniversityRating> findByStudentId(int id);
}
