package com.unifei.ProfScore.dto.UniversityRating;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UniversityRatingCreateDTO {
    @NotBlank
    private int universityId;
    @NotBlank
    private int studentId;
    private String comment;

    public UniversityRatingCreateDTO(int universityId, int studentId, String rating) {
        this.universityId = universityId;
        this.studentId = studentId;
        this.comment = rating;
    }

}
