package com.unifei.ProfScore.Service.impl;

import com.unifei.ProfScore.Service.CourseService;
import com.unifei.ProfScore.dto.Course.CourseCreateDTO;
import com.unifei.ProfScore.dto.Course.CourseResponseDTO;
import com.unifei.ProfScore.dto.Course.CourseUpdateDTO;
import com.unifei.ProfScore.model.Course;
import com.unifei.ProfScore.model.University;
import com.unifei.ProfScore.repository.CourseRepository;
import com.unifei.ProfScore.repository.UniversityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UniversityRepository universityRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UniversityRepository universityRepository) {
        this.courseRepository = courseRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    public Course getById(int id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id - " + id));
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course create(CourseCreateDTO courseCreateDTO) {
        University university = universityRepository.findByNameAndCity(
                courseCreateDTO.getUniversityName(),
                courseCreateDTO.getUniversityCity()
        ).orElseThrow(() -> new IllegalArgumentException(
                "University not found: " + courseCreateDTO.getUniversityName() +
                        " in city: " + courseCreateDTO.getUniversityCity()
        ));

        if (courseRepository.findByNameAndUniversity(courseCreateDTO.getName(), university).isPresent()) {
            throw new IllegalArgumentException("Course already exists: " + courseCreateDTO.getName() +
                    " in university: " + university.getName());
        }

        Course course = new Course();
        course.setName(courseCreateDTO.getName());
        course.setUniversity(university);

        return courseRepository.save(course);
    }

    @Override
    public Course update(int id, CourseUpdateDTO courseUpdateDTO) {
        Course existingCourse = getById(id);

        if (courseUpdateDTO.getName() != null && !courseUpdateDTO.getName().isBlank()) {
            existingCourse.setName(courseUpdateDTO.getName());
        }

        if (courseUpdateDTO.getUniversityName() != null && !courseUpdateDTO.getUniversityName().isBlank() &&
                courseUpdateDTO.getUniversityCity() != null && !courseUpdateDTO.getUniversityCity().isBlank()) {

            University university = universityRepository.findByNameAndCity(
                            courseUpdateDTO.getUniversityName(),
                            courseUpdateDTO.getUniversityCity())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "University not found with name: " + courseUpdateDTO.getUniversityName() +
                                    " and city: " + courseUpdateDTO.getUniversityCity()));

            if (courseRepository.findByNameAndUniversity(courseUpdateDTO.getName(), university).isPresent()) {
                throw new IllegalArgumentException("Course already exists: " + courseUpdateDTO.getName() +
                        " in university: " + university.getName());
            }

            existingCourse.setUniversity(university);
        }

        return courseRepository.save(existingCourse);
    }

    @Override
    public void delete(int id) {
        Course course = getById(id);
        courseRepository.delete(course);
    }

    @Override
    public CourseResponseDTO getCourseResponseDTO(Course course) {
        return new CourseResponseDTO(course);
    }
}
