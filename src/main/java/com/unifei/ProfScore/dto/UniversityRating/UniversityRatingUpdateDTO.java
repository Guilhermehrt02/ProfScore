package com.unifei.ProfScore.dto.UniversityRating;

import lombok.Data;

@Data
public class UniversityRatingUpdateDTO {

    private String comment;

    public UniversityRatingUpdateDTO(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
