package com.unifei.ProfScore.dto.ProfessorRating;

import lombok.Data;

@Data
public class ProfessorRatingResponseDTO {
    private int id;
    private int professorId;
    private int studentId;
    private int rating;
    private String comment;

    public ProfessorRatingResponseDTO(int id, int rating, int studentId, int professorId) {
        this.id = id;
        this.rating = rating;
        this.professorId = professorId;
        this.studentId = studentId;
    }
}
