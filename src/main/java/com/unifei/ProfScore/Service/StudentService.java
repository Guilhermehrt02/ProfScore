package com.unifei.ProfScore.Service;

import com.unifei.ProfScore.domain.Student;
import com.unifei.ProfScore.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService /*extends ServiceBase<Student, Integer, StudentRepository> */{

    @Autowired
    public StudentRepository studentRepository;

    public Student getById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id - " + id));
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student register(Student student) {

        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered - " + student.getEmail());
        }

        student.setId(0);
        return studentRepository.save(student);
    }

    public Student update(int id, Student updatedStudent) {

        Optional<Student> result = studentRepository.findByEmail(updatedStudent.getEmail());

        if (result.isPresent() && result.get().getId() != id) {
            throw new IllegalArgumentException("Email already registered - " + updatedStudent.getEmail());
        }

        Student existingStudent = getById(id);
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setPassword(updatedStudent.getPassword());

        return studentRepository.save(existingStudent);
    }

    public void delete(int id) {
        Student student = getById(id);
        studentRepository.delete(student);
    }
}
