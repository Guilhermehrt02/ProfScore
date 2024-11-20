package com.unifei.ProfScore.Service;

import com.unifei.ProfScore.dto.Feedback.FeedbackDTO;
import com.unifei.ProfScore.dto.UniversityRating.UniversityRatingCreateDTO;
import com.unifei.ProfScore.dto.UniversityRating.UniversityRatingResponseDTO;
import com.unifei.ProfScore.dto.UniversityRating.UniversityRatingUpdateDTO;
import com.unifei.ProfScore.model.Feedback;
import com.unifei.ProfScore.model.UniversityRating;

import java.util.List;

public interface UniversityRatingService {

    UniversityRating getById(int id);

    List<UniversityRating> getByUniversity(int id);

    List<UniversityRating> getByStudent(int id);

    UniversityRating create(UniversityRatingCreateDTO universityRatingCreateDTO);

    UniversityRating update(int id, UniversityRatingUpdateDTO universityRatingUpdateDTO);

    void delete(int id);

    UniversityRatingResponseDTO getUniversityRatingResponseDTO(UniversityRating universityRating);
}
