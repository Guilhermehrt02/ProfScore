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
        Student student = studentService.getById(professorRatingCreateDTO.getStudentId());

        ProfessorRating professorRating = new ProfessorRating();
        professorRating.setProfessor(professor);
        professorRating.setStudent(student);
        professorRating.setRating(professorRatingCreateDTO.getRating());
        professorRating.setComment(professorRatingCreateDTO.getComment());

        return professorRatingRepository.save(professorRating);
    }

    @Override
    public ProfessorRating update(int id, ProfessorRatingUpdateDTO professorRatingUpdateDTO) {

        ProfessorRating professorRating = getById(id);

        professorRating.setRating(professorRatingUpdateDTO.getRating());
        if (professorRatingUpdateDTO.getComment() != null)
            professorRating.setComment(professorRatingUpdateDTO.getComment());

        return professorRatingRepository.save(professorRating);
    }

    @Override
    public void delete(int id) {
        ProfessorRating professorRating = getById(id);
        professorRatingRepository.delete(professorRating);
    }

    @Override
    public ProfessorRatingResponseDTO getProfessorRatingResponseDTO(ProfessorRating professorRating) {

        ProfessorRatingResponseDTO response = new ProfessorRatingResponseDTO(professorRating.getId(), professorRating.getRating(), professorRating.getStudent().getId(), professorRating.getProfessor().getId());
        if (professorRating.getComment() != null) {
            response.setComment(professorRating.getComment());
        }
        return response;
    }
}
