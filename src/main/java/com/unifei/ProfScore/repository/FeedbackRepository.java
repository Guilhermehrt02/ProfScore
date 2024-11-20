package com.unifei.ProfScore.repository;

import com.unifei.ProfScore.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    List<Feedback> findByStudentId(int id);
}
