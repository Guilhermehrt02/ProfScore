package com.unifei.ProfScore.dto.UniversityRating;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UniversityRatingCreateDTO {
    @NotBlank
    private int universityId;
    @NotBlank
    private int studentId;
    @NotBlank
    private Integer rating;
    private String comment;

    public UniversityRatingCreateDTO(int universityId, int studentId, int rating) {
        this.universityId = universityId;
        this.studentId = studentId;
        this.rating = rating;
    }

}
