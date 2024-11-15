package com.unifei.ProfScore.controller;

import com.unifei.ProfScore.Service.UniversityService;
import com.unifei.ProfScore.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @GetMapping("/all")
    public ResponseEntity<List<University>> findAll() {
        List<University> university = universityService.getAll();
        return ResponseEntity.ok(university);
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> getUniversityById(@PathVariable int id) {

        University university = universityService.getById(id);

        return ResponseEntity.ok(university);
    }

    @PostMapping()
    public ResponseEntity<?> registerUniversity(@RequestBody University university) {

        University newUniversity = universityService.create(university);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUniversity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<University> updateUniversity(@PathVariable int id, @RequestBody University updatedUniversity) {

        University university = universityService.update(id, updatedUniversity);

        return ResponseEntity.ok(university);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUniversity(@PathVariable int id) {

        universityService.delete(id);

        return ResponseEntity.ok("University has been successfully deleted.");
    }
}
