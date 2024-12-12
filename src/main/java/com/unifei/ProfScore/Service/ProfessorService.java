package com.unifei.ProfScore.Service;

import java.util.List;

import com.unifei.ProfScore.dto.Professor.ProfessorResponseDTO;
import com.unifei.ProfScore.model.Professor;

public interface ProfessorService {
    Professor getById(int id);

    List<Professor> getAll();

    Professor create(Professor professor);

    Professor update(int id, Professor professor);

    void delete(int id);

    ProfessorResponseDTO getProfessorResponseDTO(Professor professor);
}
