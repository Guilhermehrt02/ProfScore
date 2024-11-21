package com.unifei.ProfScore.Service;

import com.unifei.ProfScore.dto.ProfessorRating.ProfessorRatingCreateDTO;
import com.unifei.ProfScore.dto.ProfessorRating.ProfessorRatingResponseDTO;
import com.unifei.ProfScore.dto.ProfessorRating.ProfessorRatingUpdateDTO;
import com.unifei.ProfScore.model.ProfessorRating;
import com.unifei.ProfScore.model.UniversityRating;

import java.util.List;

public interface ProfessorRatingService {

    UniversityRating getById(int id);

    List<ProfessorRating> getByUniversity(int id);

    List<ProfessorRating> getByStudent(int id);

    ProfessorRating create(ProfessorRatingCreateDTO ProfessorRatingCreateDTO);

    ProfessorRating update(int id, ProfessorRatingUpdateDTO ProfessorRatingUpdateDTO);

    void delete(int id);

    ProfessorRatingResponseDTO getProfessorRatingResponseDTO(ProfessorRating professorRating);
}
