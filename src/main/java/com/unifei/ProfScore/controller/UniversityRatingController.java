package com.unifei.ProfScore.controller;

import com.unifei.ProfScore.Service.UniversityRatingService;
import com.unifei.ProfScore.dto.UniversityRating.UniversityRatingCreateDTO;
import com.unifei.ProfScore.dto.UniversityRating.UniversityRatingResponseDTO;
import com.unifei.ProfScore.dto.UniversityRating.UniversityRatingUpdateDTO;
import com.unifei.ProfScore.model.UniversityRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/university-rating")
public class UniversityRatingController {

    @Autowired
    private UniversityRatingService universityRatingService;

    @GetMapping("/{id}")
    public ResponseEntity<UniversityRatingResponseDTO> getUniversityRatingById(@PathVariable int id) {

        UniversityRating universityRating = universityRatingService.getById(id);

        return ResponseEntity.ok(universityRatingService.getUniversityRatingResponseDTO(universityRating));
    }

    @GetMapping("/university/{id}")
    public ResponseEntity<List<UniversityRatingResponseDTO>> getUniversityRatingByUniversity(@PathVariable int id) {

        List<UniversityRatingResponseDTO> universityRatings = universityRatingService.getByUniversity(id)
                .stream().map(universityRating -> universityRatingService.getUniversityRatingResponseDTO(universityRating))
                .toList();

        return ResponseEntity.ok(universityRatings);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<UniversityRatingResponseDTO>> getUniversityRatingByStudent(@PathVariable int id) {

        List<UniversityRatingResponseDTO> universityRatings = universityRatingService.getByStudent(id)
                .stream().map(universityRating -> universityRatingService.getUniversityRatingResponseDTO(universityRating))
                .toList();

        return ResponseEntity.ok(universityRatings);
    }

    @PostMapping()
    public ResponseEntity<?> registerUniversityRating(@RequestBody UniversityRatingCreateDTO universityRating) {

        UniversityRating newUniversityRating = universityRatingService.create(universityRating);

        return ResponseEntity.status(HttpStatus.CREATED).body(universityRatingService.getUniversityRatingResponseDTO(newUniversityRating));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniversityRatingResponseDTO> updateUniversityRating(@PathVariable int id, @RequestBody UniversityRatingUpdateDTO universityRatingUpdateDTO) {

        UniversityRating universityRating = universityRatingService.update(id, universityRatingUpdateDTO);

        return ResponseEntity.ok(universityRatingService.getUniversityRatingResponseDTO(universityRating));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUniversityRating(@PathVariable int id) {

        universityRatingService.delete(id);

        return ResponseEntity.ok("University Rating deleted successfully");
    }

}
