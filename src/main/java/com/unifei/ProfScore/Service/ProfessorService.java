package com.unifei.ProfScore.Service;

import com.unifei.ProfScore.dto.Professor.ProfessorCreateDTO;
import com.unifei.ProfScore.dto.Professor.ProfessorResponseDTO;
import com.unifei.ProfScore.dto.Professor.ProfessorUpdateDTO;
import com.unifei.ProfScore.model.Professor;

import java.util.List;

public interface ProfessorService {
    Professor getById(int id);

    List<Professor> getAll();

    Professor create(Professor professor);

    Professor update(int id, Professor professor);

    void delete(int id);

    ProfessorResponseDTO getProfessorResponseDTO(Professor professor);
}
