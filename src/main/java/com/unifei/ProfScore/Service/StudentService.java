package com.unifei.ProfScore.Service;

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

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    public StudentRepository studentRepository;

    @Autowired
    public CourseRepository courseRepository;

    @Autowired
    public UniversityRepository universityRepository;

    public Student getById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id - " + id));
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student create(StudentCreateDTO studentDTO) {

        // Verificar se o e-mail já está registrado
        if (studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered - " + studentDTO.getEmail());
        }

        // Criar a entidade Student a partir do DTO
        Student student = new Student(studentDTO.getName(), studentDTO.getEmail(), studentDTO.getPassword());

        if (!validateEntryYear(studentDTO.getEntryYear())) {
            throw new IllegalArgumentException("Invalid entry year: " + studentDTO.getEntryYear());
        }
        student.setEntryYear(studentDTO.getEntryYear());

        // Se curso e universidade forem fornecidos, associar o curso
        if (studentDTO.getCourseName() != null && studentDTO.getUniversityName() != null) {
            // Buscar a universidade pelo nome
            University university = universityRepository.findByName(studentDTO.getUniversityName())
                    .orElseThrow(() -> new IllegalArgumentException("University not found: " + studentDTO.getUniversityName()));

            // Buscar o curso pelo nome e universidade
            Course course = courseRepository.findByNameAndUniversity(studentDTO.getCourseName(), university)
                    .orElseThrow(() -> new IllegalArgumentException("Course not found: " + studentDTO.getCourseName() + " in university: " + studentDTO.getUniversityName()));

            student.setCourse(course);
        }

        // Salvar o estudante no banco
        return studentRepository.save(student);
    }

    public Student update(int id, StudentUpdateDTO updatedStudent) {
        // Verifique se o estudante existe
        Student student = getById(id);

        // Verifique se o email fornecido está associado a outro estudante
        if (updatedStudent.getEmail() != null) {

            Optional<Student> optionalStudent = studentRepository.findByEmail(updatedStudent.getEmail());

            if (optionalStudent.isPresent() && optionalStudent.get().getId() != id) {
                throw new IllegalArgumentException("Email already registered - " + updatedStudent.getEmail());
            }
            student.setEmail(updatedStudent.getEmail());
        }

        // Atualize apenas os campos fornecidos
        if (updatedStudent.getName() != null) {
            student.setName(updatedStudent.getName());
        }

        if (updatedStudent.getEntryYear() != null && validateEntryYear(updatedStudent.getEntryYear())) {
            student.setEntryYear(updatedStudent.getEntryYear());
        }

        if (updatedStudent.getPassword() != null) {
            student.setPassword(updatedStudent.getPassword());
        }

        // Verifique se o curso deve ser atualizado
        if (updatedStudent.getCourseName() != null && updatedStudent.getUniversityName() != null) {
            // Busque a universidade pelo nome
            University university = universityRepository.findByName(updatedStudent.getUniversityName())
                    .orElseThrow(() -> new IllegalArgumentException("University not found: " + updatedStudent.getUniversityName()));

            // Busque o curso pelo nome e pela universidade
            Course course = courseRepository.findByNameAndUniversity(updatedStudent.getCourseName(), university)
                    .orElseThrow(() -> new IllegalArgumentException("Course not found: " + updatedStudent.getCourseName() + " in university: " + updatedStudent.getUniversityName()));

            student.setCourse(course);
        } else {
            student.setCourse(null); // Permite remover o curso associado
        }

        // Salve as mudanças no banco de dados
        return studentRepository.save(student);
    }

    public void delete(int id) {
        Student student = getById(id);
        studentRepository.delete(student);
    }

    private boolean validateEntryYear(@NotBlank(message = "Entry year is mandatory") Integer entryYear) {

        return entryYear > 1980 && entryYear <= java.time.Year.now().getValue();
    }
}
