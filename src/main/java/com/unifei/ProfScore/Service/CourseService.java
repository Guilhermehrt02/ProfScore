package com.unifei.ProfScore.Service;

import com.unifei.ProfScore.dto.Course.CourseCreateDTO;
import com.unifei.ProfScore.dto.Course.CourseResponseDTO;
import com.unifei.ProfScore.dto.Course.CourseUpdateDTO;
import com.unifei.ProfScore.model.Course;

import java.util.List;

public interface CourseService {

    Course getById(int id);

    List<Course> getAll();

    Course create(CourseCreateDTO courseCreateDTO);

    Course update(int id, CourseUpdateDTO courseUpdateDTO);

    void delete(int id);

    CourseResponseDTO getCourseResponseDTO(Course course);
}
