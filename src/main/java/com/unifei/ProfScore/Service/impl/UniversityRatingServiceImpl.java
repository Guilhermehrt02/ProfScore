package com.unifei.ProfScore.Service.impl;

import com.unifei.ProfScore.Service.StudentService;
import com.unifei.ProfScore.Service.UniversityRatingService;
import com.unifei.ProfScore.Service.UniversityService;
import com.unifei.ProfScore.domain.Student;
import com.unifei.ProfScore.dto.UniversityRating.UniversityRatingCreateDTO;
import com.unifei.ProfScore.dto.UniversityRating.UniversityRatingResponseDTO;
import com.unifei.ProfScore.dto.UniversityRating.UniversityRatingUpdateDTO;
import com.unifei.ProfScore.model.University;
import com.unifei.ProfScore.model.UniversityRating;
import com.unifei.ProfScore.repository.UniversityRatingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityRatingServiceImpl implements UniversityRatingService {

    private UniversityRatingRepository universityRatingRepository;
    private UniversityService universityService;
    private StudentService studentService;

    @Autowired
    public UniversityRatingServiceImpl(UniversityRatingRepository universityRatingRepository, UniversityService universityService, StudentService studentService) {
        this.universityRatingRepository = universityRatingRepository;
        this.universityService = universityService;
        this.studentService = studentService;
    }

    @Override
    public UniversityRating getById(int id) {
        return universityRatingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UniversityRating not found with id - " + id));
    }

    @Override
    public List<UniversityRating> getByUniversity(int id) {

        University university = universityService.getById(id);

        return universityRatingRepository.findByUniversityId(university.getId());
    }

    @Override
    public List<UniversityRating> getByStudent(int id) {

        Student student = studentService.getById(id);

        return universityRatingRepository.findByStudentId(student.getId());
    }

    @Override
    public UniversityRating create(UniversityRatingCreateDTO universityRatingCreateDTO) {

        University university = universityService.getById(universityRatingCreateDTO.getUniversityId());
        Student student = studentService.getById(universityRatingCreateDTO.getStudentId());

        UniversityRating universityRating = new UniversityRating(universityRatingCreateDTO.getComment(), student, university);

        return universityRatingRepository.save(universityRating);
    }

    @Override
    public UniversityRating update(int id, UniversityRatingUpdateDTO universityRatingUpdateDTO) {

        UniversityRating existingUniversityRating = getById(id);

        existingUniversityRating.setComment(universityRatingUpdateDTO.getComment());

        return universityRatingRepository.save(existingUniversityRating);
    }

    @Override
    public void delete(int id) {
        UniversityRating universityRating = getById(id);
        universityRatingRepository.delete(universityRating);
    }

    @Override
    public UniversityRatingResponseDTO getUniversityRatingResponseDTO(UniversityRating universityRating) {
        return new UniversityRatingResponseDTO(universityRating.getId(), universityRating.getComment(), universityRating.getStudent().getId(), universityRating.getUniversity().getId());
    }

}
