package com.unifei.ProfScore.Service;

import com.unifei.ProfScore.domain.Student;
import com.unifei.ProfScore.dto.Student.StudentCreateDTO;
import com.unifei.ProfScore.dto.Student.StudentUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student getById(int id);

    Optional<Student> getByEmail(String email);

    List<Student> getAll();

    Student create(StudentCreateDTO studentDTO);

    Student update(int id, StudentUpdateDTO updatedStudent);

    void delete(int id);
}
