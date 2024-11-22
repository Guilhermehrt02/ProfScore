package com.unifei.ProfScore.Service.impl;

import com.unifei.ProfScore.Service.ProfessorRatingService;
import com.unifei.ProfScore.Service.ProfessorService;
import com.unifei.ProfScore.Service.StudentService;
import com.unifei.ProfScore.domain.Student;
import com.unifei.ProfScore.dto.ProfessorRating.ProfessorRatingResponseDTO;
import com.unifei.ProfScore.dto.ProfessorRating.ProfessorRatingUpdateDTO;
import com.unifei.ProfScore.dto.ProfessorRating.ProfessorRatingCreateDTO;
import com.unifei.ProfScore.model.Professor;
import com.unifei.ProfScore.model.ProfessorRating;
import com.unifei.ProfScore.repository.ProfessorRatingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorRatingServiceImpl implements ProfessorRatingService {

    private ProfessorRatingRepository professorRatingRepository;
    private ProfessorService professorService;
    private StudentService studentService;

    @Autowired
    public ProfessorRatingServiceImpl(ProfessorRatingRepository professorRatingRepository, ProfessorService professorService, StudentService studentService) {
        this.professorRatingRepository = professorRatingRepository;
        this.professorService = professorService;
        this.studentService = studentService;
    }

    @Override
    public ProfessorRating getById(int id) {
        return professorRatingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProfessorRating not found with id - " + id));
    }

    @Override
    public List<ProfessorRating> getByProfessor(int id) {
        Professor professor = professorService.getById(id);
        return professorRatingRepository.findByProfessorId(professor.getId());
    }

    @Override
    public List<ProfessorRating> getByStudent(int id) {
        Student student = studentService.getById(id);
        return professorRatingRepository.findByStudentId(student.getId());
    }

    @Override
    public ProfessorRating create(ProfessorRatingCreateDTO professorRatingCreateDTO) {
        Professor professor = professorService.getById(professorRatingCreateDTO.getProfessorId());

        // Atualizar os campos do professor
        Integer newRating = professorRatingCreateDTO.getRating();
        professor.setTotalRatings(professor.getTotalRatings() + 1);
        professor.setSumRatings(professor.getSumRatings() + newRating);
        professor.setScore(professor.getSumRatings() / professor.getTotalRatings());

        Student student = studentService.getById(professorRatingCreateDTO.getStudentId());

        // Criar um novo rating
        ProfessorRating professorRating = new ProfessorRating();
        if (newRating < 0 || newRating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        if (professorRatingCreateDTO.getComment() != null) {
            if (professorRatingCreateDTO.getComment().length() > 255) {
                throw new IllegalArgumentException("Comment must have less than 255 characters");
            }
            professorRating.setComment(professorRatingCreateDTO.getComment());
        }
        professorRating.setProfessor(professor);
        professorRating.setStudent(student);
        professorRating.setRating(newRating);

        // Persistir o rating e atualizar o professor
        professorService.update(professor.getId(), professor);
        return professorRatingRepository.save(professorRating);
    }

    @Override
    public ProfessorRating update(int id, ProfessorRatingUpdateDTO professorRatingUpdateDTO) {
        ProfessorRating existingProfessorRating = getById(id);

        Professor professor = existingProfessorRating.getProfessor();
        Integer oldRating = existingProfessorRating.getRating();
        Integer newRating = professorRatingUpdateDTO.getRating();

        if (newRating < 0 || newRating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        if (professorRatingUpdateDTO.getComment().length() > 255) {
            throw new IllegalArgumentException("Comment must have less than 255 characters");
        }

        // Atualizar os campos do professor
        professor.setSumRatings(professor.getSumRatings() - oldRating + newRating);
        professor.setScore(professor.getSumRatings() / professor.getTotalRatings());

        // Atualizar os dados do rating
        existingProfessorRating.setRating(newRating);
        if (professorRatingUpdateDTO.getComment() != null) {
            existingProfessorRating.setComment(professorRatingUpdateDTO.getComment());
        }

        // Persistir o rating e atualizar o professor
        professorService.update(professor.getId(), professor);
        return professorRatingRepository.save(existingProfessorRating);
    }

    @Override
    public void delete(int id) {
        ProfessorRating professorRating = getById(id);

        Professor professor = professorRating.getProfessor();
        Integer oldRating = professorRating.getRating();

        // Atualizar os campos do professor
        professor.setTotalRatings(professor.getTotalRatings() - 1);
        professor.setSumRatings(professor.getSumRatings() - oldRating);
        professor.setScore(professor.getTotalRatings() > 0
                ? professor.getSumRatings() / professor.getTotalRatings()
                : 0);

        // Persistir a atualização no professor e remover o rating
        professorService.update(professor.getId(), professor);
        professorRatingRepository.delete(professorRating);
    }

    @Override
    public ProfessorRatingResponseDTO getProfessorRatingResponseDTO(ProfessorRating professorRating) {
        ProfessorRatingResponseDTO response = new ProfessorRatingResponseDTO(
                professorRating.getId(),
                professorRating.getRating(),
                professorRating.getStudent().getId(),
                professorRating.getProfessor().getId()
        );
        if (professorRating.getComment() != null) {
            response.setComment(professorRating.getComment());
        }
        return response;
    }
}
