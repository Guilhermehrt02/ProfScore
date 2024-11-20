package com.unifei.ProfScore.controller;

import com.unifei.ProfScore.Service.ProfessorService;
import com.unifei.ProfScore.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professor")
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
