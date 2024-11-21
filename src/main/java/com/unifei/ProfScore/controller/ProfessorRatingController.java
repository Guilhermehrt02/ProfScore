package com.unifei.ProfScore.controller;

import com.unifei.ProfScore.Service.ProfessorRatingService;
import com.unifei.ProfScore.dto.ProfessorRating.ProfessorRatingCreateDTO;
import com.unifei.ProfScore.dto.ProfessorRating.ProfessorRatingResponseDTO;
import com.unifei.ProfScore.dto.ProfessorRating.ProfessorRatingUpdateDTO;
import com.unifei.ProfScore.model.ProfessorRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professor-rating")
public class ProfessorRatingController {

    @Autowired
    private ProfessorRatingService professorRatingService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorRatingResponseDTO> getProfessorRatingById(@PathVariable int id) {

        ProfessorRating professorRating = professorRatingService.getById(id);

        return ResponseEntity.ok(professorRatingService.getProfessorRatingResponseDTO(professorRating));
    }

    @GetMapping("/professor/{id}")
    public ResponseEntity<List<ProfessorRatingResponseDTO>> getProfessorRatingByProfessor(@PathVariable int id) {

        List<ProfessorRatingResponseDTO> professorRatings = professorRatingService.getByProfessor(id)
                .stream().map(professorRating -> professorRatingService.getProfessorRatingResponseDTO(professorRating))
                .toList();

        return ResponseEntity.ok(professorRatings);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<ProfessorRatingResponseDTO>> getProfessorRatingByStudent(@PathVariable int id) {

        List<ProfessorRatingResponseDTO> professorRatings = professorRatingService.getByStudent(id)
                .stream().map(professorRating -> professorRatingService.getProfessorRatingResponseDTO(professorRating))
                .toList();

        return ResponseEntity.ok(professorRatings);
    }

    @PostMapping()
    public ResponseEntity<?> registerProfessorRating(@RequestBody ProfessorRatingCreateDTO professorRating) {

        ProfessorRating newProfessorRating = professorRatingService.create(professorRating);

        return ResponseEntity.status(HttpStatus.CREATED).body(professorRatingService.getProfessorRatingResponseDTO(newProfessorRating));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorRatingResponseDTO> updateProfessorRating(@PathVariable int id, @RequestBody ProfessorRatingUpdateDTO professorRatingUpdateDTO) {

        ProfessorRating updatedProfessorRating = professorRatingService.update(id, professorRatingUpdateDTO);

        return ResponseEntity.ok(professorRatingService.getProfessorRatingResponseDTO(updatedProfessorRating));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfessorRating(@PathVariable int id) {

        professorRatingService.delete(id);

        return ResponseEntity.ok("ProfessorRating deleted successfully");
    }
}
