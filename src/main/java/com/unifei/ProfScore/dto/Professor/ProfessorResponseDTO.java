package com.unifei.ProfScore.dto.Professor;

import com.unifei.ProfScore.model.Course;
import com.unifei.ProfScore.model.Professor;
import com.unifei.ProfScore.model.ProfessorRating;
import lombok.Data;

import java.util.List;

@Data
public class ProfessorResponseDTO {
    private int id;
    private String name;
    private float score;
    List<ProfessorRating> rating;

    // Construtor para facilitar a conversão
    public ProfessorResponseDTO(Professor professor) {
        this.id = professor.getId();
        this.name = professor.getName();
        this.score = professor.getScore();
        this.rating = professor.getRating();
    }
}
