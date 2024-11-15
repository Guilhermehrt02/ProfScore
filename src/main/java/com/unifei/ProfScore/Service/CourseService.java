package com.unifei.ProfScore.Service;

import com.unifei.ProfScore.domain.Student;
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
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UniversityRepository universityRepository;

    public Course getById(int id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id - " + id));
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course create(CourseCreateDTO courseCreateDTO) {

        // Buscar universidade pelo nome e cidade
        University university = universityRepository.findByNameAndCity(
                courseCreateDTO.getUniversityName(),
                courseCreateDTO.getUniversityCity()
        ).orElseThrow(() -> new IllegalArgumentException(
                "University not found: " + courseCreateDTO.getUniversityName() +
                        " in city: " + courseCreateDTO.getUniversityCity()
        ));

        // Verificar se o curso já existe na universidade
        if (courseRepository.findByNameAndUniversity(courseCreateDTO.getName(), university).isPresent()) {
            throw new IllegalArgumentException("Course already exists: " + courseCreateDTO.getName() +
                    " in university: " + university.getName());
        }

        // Criar o curso
        Course course = new Course();
        course.setName(courseCreateDTO.getName());
        course.setUniversity(university);

        // Salvar no repositório
        return courseRepository.save(course);
    }

    public Course update(int id, CourseUpdateDTO courseUpdateDTO) {

        Course existingCourse = getById(id);

        if (courseUpdateDTO.getName() != null && !courseUpdateDTO.getName().isBlank())
            existingCourse.setName(courseUpdateDTO.getName());

        if (courseUpdateDTO.getUniversityName() != null && !courseUpdateDTO.getUniversityName().isBlank() &&
                courseUpdateDTO.getUniversityCity() != null && !courseUpdateDTO.getUniversityCity().isBlank()) {

            // Busca a universidade pelo nome e cidade
            University university = universityRepository.findByNameAndCity(
                            courseUpdateDTO.getUniversityName(),
                            courseUpdateDTO.getUniversityCity())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "University not found with name: " + courseUpdateDTO.getUniversityName() +
                                    " and city: " + courseUpdateDTO.getUniversityCity()));

            // Verificar se o curso já existe na universidade
            if (courseRepository.findByNameAndUniversity(courseUpdateDTO.getName(), university).isPresent()) {
                throw new IllegalArgumentException("Course already exists: " + courseUpdateDTO.getName() +
                        " in university: " + university.getName());
            }
            // Atualiza a universidade do curso
            existingCourse.setUniversity(university);
        }

        return courseRepository.save(existingCourse);
    }

    public void delete(int id) {
        Course course = getById(id);
        courseRepository.delete(course);
    }

    public CourseResponseDTO getCourseResponseDTO(Course course) {
        return new CourseResponseDTO(course);
    }
}
