package com.unifei.ProfScore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unifei.ProfScore.Service.ProfessorService;
import com.unifei.ProfScore.model.Professor;

@RestController
@RequestMapping("/api/professor")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/all")
    public ResponseEntity<List<Professor>> findAll() {
        List<Professor> professor = professorService.getAll();
        return ResponseEntity.ok(professor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable int id) {

        Professor professor = professorService.getById(id);

        return ResponseEntity.ok(professor);
    }

    @PostMapping()
    public ResponseEntity<?> registerProfessor(@RequestBody Professor professor) {

        Professor newProfessor = professorService.create(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProfessor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> update(@PathVariable int id, @RequestBody Professor updatedProfessor) {

        Professor professor = professorService.update(id, updatedProfessor);

        return ResponseEntity.ok(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfessor(@PathVariable int id) {

        professorService.delete(id);

        return ResponseEntity.ok("Professor has been successfully deleted.");
    }
}
