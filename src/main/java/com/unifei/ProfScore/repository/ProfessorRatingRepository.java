package com.unifei.ProfScore.repository;

import com.unifei.ProfScore.model.ProfessorRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRatingRepository extends JpaRepository<ProfessorRating, Integer> {
    List<ProfessorRating> findByProfessorId(int id);

    List<ProfessorRating> findByStudentId(int id);
}
