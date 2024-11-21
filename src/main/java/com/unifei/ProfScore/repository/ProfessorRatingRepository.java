package com.unifei.ProfScore.repository;

import com.unifei.ProfScore.model.ProfessorRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRatingRepository extends JpaRepository<ProfessorRating, Integer> {
//    List<ProfessorRating> findByProfessor(Professor professor);
}
