package com.unifei.ProfScore.dto.UniversityRating;

import lombok.Data;

@Data
public class UniversityRatingUpdateDTO {

    private String comment;
    private Integer rating;

    public UniversityRatingUpdateDTO(String comment, int rating) {
        this.comment = comment;
        this.rating = rating;
    }

}
