package com.unifei.ProfScore.Service.impl;

import com.unifei.ProfScore.Service.StudentService;
import com.unifei.ProfScore.domain.Student;
import com.unifei.ProfScore.dto.Student.StudentCreateDTO;
import com.unifei.ProfScore.dto.Student.StudentUpdateDTO;
import com.unifei.ProfScore.model.Course;
import com.unifei.ProfScore.model.University;
import com.unifei.ProfScore.repository.CourseRepository;
import com.unifei.ProfScore.repository.StudentRepository;
import com.unifei.ProfScore.repository.UniversityRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final UniversityRepository universityRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, UniversityRepository universityRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    public Student getById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id - " + id));
    }

    @Override
    public Optional<Student> getByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student create(StudentCreateDTO studentDTO) {
        if (studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered - " + studentDTO.getEmail());
        }

        Student student = new Student(studentDTO.getName(), studentDTO.getEmail(), studentDTO.getPassword());

        if (!validateEntryYear(studentDTO.getEntryYear())) {
            throw new IllegalArgumentException("Invalid entry year: " + studentDTO.getEntryYear());
        }
        student.setEntryYear(studentDTO.getEntryYear());

        if (studentDTO.getCourseName() != null && studentDTO.getUniversityName() != null) {
            University university = universityRepository.findByName(studentDTO.getUniversityName())
                    .orElseThrow(() -> new IllegalArgumentException("University not found: " + studentDTO.getUniversityName()));

            Course course = courseRepository.findByNameAndUniversity(studentDTO.getCourseName(), university)
                    .orElseThrow(() -> new IllegalArgumentException("Course not found: " + studentDTO.getCourseName() + " in university: " + studentDTO.getUniversityName()));

            student.setCourse(course);
        }

        return studentRepository.save(student);
    }

    @Override
    public Student update(int id, StudentUpdateDTO updatedStudent) {
        Student student = getById(id);

        if (updatedStudent.getEmail() != null) {
            Optional<Student> optionalStudent = studentRepository.findByEmail(updatedStudent.getEmail());
            if (optionalStudent.isPresent() && optionalStudent.get().getId() != id) {
                throw new IllegalArgumentException("Email already registered - " + updatedStudent.getEmail());
            }
            student.setEmail(updatedStudent.getEmail());
        }

        if (updatedStudent.getName() != null) {
            student.setName(updatedStudent.getName());
        }

        if (updatedStudent.getEntryYear() != null && validateEntryYear(updatedStudent.getEntryYear())) {
            student.setEntryYear(updatedStudent.getEntryYear());
        }

        if (updatedStudent.getPassword() != null) {
            student.setPassword(updatedStudent.getPassword());
        }

        if (updatedStudent.getCourseName() != null && updatedStudent.getUniversityName() != null) {
            University university = universityRepository.findByName(updatedStudent.getUniversityName())
                    .orElseThrow(() -> new IllegalArgumentException("University not found: " + updatedStudent.getUniversityName()));

            Course course = courseRepository.findByNameAndUniversity(updatedStudent.getCourseName(), university)
                    .orElseThrow(() -> new IllegalArgumentException("Course not found: " + updatedStudent.getCourseName() + " in university: " + updatedStudent.getUniversityName()));

            student.setCourse(course);
        } else {
            student.setCourse(null);
        }

        return studentRepository.save(student);
    }

    @Override
    public void delete(int id) {
        Student student = getById(id);
        studentRepository.delete(student);
    }

    private boolean validateEntryYear(@NotBlank Integer entryYear) {
        return entryYear > 1980 && entryYear <= java.time.Year.now().getValue();
    }
}
