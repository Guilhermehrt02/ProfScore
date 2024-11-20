package com.unifei.ProfScore.dto.Feedback;

import com.unifei.ProfScore.model.Feedback;
import lombok.Data;

@Data
public class FeedbackResponseDTO {

    private int id;
    private String feedback;
    private int studentId;

    public FeedbackResponseDTO(int id, String feedback, int id1) {
        this.id = id;
        this.feedback = feedback;
        this.studentId = id1;
    }

}
