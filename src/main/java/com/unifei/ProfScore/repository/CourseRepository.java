package com.unifei.ProfScore.repository;

import com.unifei.ProfScore.model.Course;
import com.unifei.ProfScore.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findByNameAndUniversity(String name, University university);
}
