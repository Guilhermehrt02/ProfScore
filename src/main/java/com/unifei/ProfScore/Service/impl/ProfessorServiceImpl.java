package com.unifei.ProfScore.Service.impl;

import com.unifei.ProfScore.Service.ProfessorService;
import com.unifei.ProfScore.dto.Professor.ProfessorCreateDTO;
import com.unifei.ProfScore.dto.Professor.ProfessorResponseDTO;
import com.unifei.ProfScore.dto.Professor.ProfessorUpdateDTO;
import com.unifei.ProfScore.model.Professor;
import com.unifei.ProfScore.model.Professor;
import com.unifei.ProfScore.repository.ProfessorRepository;
import com.unifei.ProfScore.repository.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public Professor getById(int id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found with id - " + id));
    }

    @Override
    public List<Professor> getAll() {
        return professorRepository.findAll();
    }

    @Override
    public Professor create(Professor professor) {
        Professor newProfessor = new Professor();
        newProfessor.setName(professor.getName());
        newProfessor.setScore(professor.getScore());

        return professorRepository.save(newProfessor);
    }

    @Override
    public Professor update(int id, Professor professor) {
        Professor existingProfessor = getById(id);

        if (professor.getName() != null && !professor.getName().isBlank()) {
            existingProfessor.setName(professor.getName());
        }

//        if (professor.getName() != null && !professor.getName().isBlank()) {
//
//            Professor newProfessor = professorRepository.findByName(professor.getName())
//                    .orElseThrow(() -> new IllegalArgumentException(
//                            "Professor not found with name: " + professor.getName()));
//
//            if (professorRepository.findByName(professor.getName()).isPresent()) {
//                throw new IllegalArgumentException("Professor already exists: " + professor.getName() +
//                        " in professor: " + newProfessor.getName());
//            }
//
//            existingProfessor.setName(professor.getName());
//
//        }

        return professorRepository.save(existingProfessor);
    }

    @Override
    public void delete(int id) {
        Professor professor = getById(id);
        professorRepository.delete(professor);
    }

    @Override
    public ProfessorResponseDTO getProfessorResponseDTO(Professor professor) {
        return new ProfessorResponseDTO(professor);
    }
}
