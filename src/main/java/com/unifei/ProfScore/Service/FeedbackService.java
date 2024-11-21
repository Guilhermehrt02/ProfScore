package com.unifei.ProfScore.Service;

import com.unifei.ProfScore.dto.Feedback.FeedbackDTO;
import com.unifei.ProfScore.dto.Feedback.FeedbackResponseDTO;
import com.unifei.ProfScore.model.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback getById(int id);

    List<Feedback> getAll();

    Feedback create(FeedbackDTO feedbackDTO);

    Feedback update(int id, FeedbackDTO feedbackDTO);

    void delete(int id);

    FeedbackResponseDTO getFeedbackResponseDTO(Feedback feedback);

    List<Feedback> getByStudent(int id);
}
