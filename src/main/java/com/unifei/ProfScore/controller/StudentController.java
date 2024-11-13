package com.unifei.ProfScore.controller;

import com.unifei.ProfScore.Service.StudentService;
import com.unifei.ProfScore.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
        public ResponseEntity<List<Student>> findAll() {
        List<Student> students = studentService.getAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {

        Student student = studentService.getById(id);

        return ResponseEntity.ok(student);
    }

    @PostMapping()
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {

        Student newStudent = studentService.register(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student updatedClient) {

        Student student = studentService.update(id, updatedClient);

        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {

        studentService.delete(id);

        return ResponseEntity.ok("Student has been successfully deleted.");
    }
}
