package com.unifei.ProfScore.dto.UniversityRating;

import lombok.Data;

@Data
public class UniversityRatingResponseDTO {

    private int id;
    private String comment;
    private int rating;
    private int studentId;
    private int universityId;

    public UniversityRatingResponseDTO(int id, int studentId, int universityId, int rating) {
        this.id = id;
        this.studentId = studentId;
        this.universityId = universityId;
        this.rating = rating;
    }
}
