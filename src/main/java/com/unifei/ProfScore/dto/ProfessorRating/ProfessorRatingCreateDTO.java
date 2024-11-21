package com.unifei.ProfScore.dto.ProfessorRating;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfessorRatingCreateDTO {

    @NotBlank
    private int professorId;
    @NotBlank
    private int studentId;
    @NotBlank
    private int rating;
    private String comment;
}
