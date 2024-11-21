package com.unifei.ProfScore.dto.UniversityRating;

import lombok.Data;

@Data
public class UniversityRatingResponseDTO {

    private int id;
    private String comment;
    private int studentId;
    private int universityId;

    public UniversityRatingResponseDTO(int id, String comment, int studentId, int universityId) {
        this.id = id;
        this.comment = comment;
        this.studentId = studentId;
        this.universityId = universityId;
    }
}
