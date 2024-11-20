package com.unifei.ProfScore.dto.Feedback;

import lombok.Data;

@Data
public class FeedbackDTO {

    private String feedback;
    private int studentId;

    public FeedbackDTO(String feedback, int studentId) {
        this.feedback = feedback;
        this.studentId = studentId;
    }

}
