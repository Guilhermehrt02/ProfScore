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

        // Atualizar os campos da universidade
        Integer newRating = universityRatingCreateDTO.getRating();
        university.setTotalRatings(university.getTotalRatings() + 1);
        university.setSumRatings(university.getSumRatings() + newRating);
        university.setScore(university.getSumRatings() / university.getTotalRatings());

        Student student = studentService.getById(universityRatingCreateDTO.getStudentId());

        // Criar um novo rating
        UniversityRating universityRating = new UniversityRating();
        if (newRating < 0 || newRating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }

        if(universityRatingCreateDTO.getComment()!=null) {
            universityRating.setComment(universityRatingCreateDTO.getComment());
            if (universityRatingCreateDTO.getComment().length() > 255) {
                throw new IllegalArgumentException("Comment must have less than 255 characters");
            }
        }
        universityRating.setRating(newRating);
        universityRating.setStudent(student);
        universityRating.setUniversity(university);

        // Persistir o rating e atualizar a universidade
        universityService.update(university.getId(), university);
        return universityRatingRepository.save(universityRating);

    }

    @Override
    public UniversityRating update(int id, UniversityRatingUpdateDTO universityRatingUpdateDTO) {

        UniversityRating existingUniversityRating = getById(id);

        University university = existingUniversityRating.getUniversity();
        Integer oldRating = existingUniversityRating.getRating();
        Integer newRating = universityRatingUpdateDTO.getRating();

        if (newRating < 0 || newRating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }

        // Atualizar os campos da universidade
        university.setSumRatings(university.getSumRatings() - oldRating + newRating);
        university.setScore(university.getSumRatings() / university.getTotalRatings());

        // Atualizar os dados do rating
        if (universityRatingUpdateDTO.getComment() != null) {
            existingUniversityRating.setComment(universityRatingUpdateDTO.getComment());

            if (universityRatingUpdateDTO.getComment().length() > 255) {
                throw new IllegalArgumentException("Comment must have less than 255 characters");
            }
        }
        existingUniversityRating.setRating(newRating);

        // Persistir o rating e atualizar a universidade
        universityService.update(university.getId(), university);
        return universityRatingRepository.save(existingUniversityRating);
    }

    @Override
    public void delete(int id) {
        UniversityRating universityRating = getById(id);

        University university = universityRating.getUniversity();
        Integer oldRating = universityRating.getRating();

        // Atualizar os campos da universidade
        university.setTotalRatings(university.getTotalRatings() - 1);
        university.setSumRatings(university.getSumRatings() - oldRating);
        university.setScore(university.getTotalRatings() > 0
                ? university.getSumRatings() / university.getTotalRatings()
                : 0);

        // Persistir a atualização na universidade e remover o rating
        universityService.update(university.getId(), university);
        universityRatingRepository.delete(universityRating);
    }

    @Override
    public UniversityRatingResponseDTO getUniversityRatingResponseDTO(UniversityRating universityRating) {
        UniversityRatingResponseDTO response = new UniversityRatingResponseDTO(
                                                    universityRating.getId(),
                                                    universityRating.getStudent().getId(),
                                                    universityRating.getUniversity().getId(),
                                                    universityRating.getRating());
        if (universityRating.getComment() != null) {
            response.setComment(universityRating.getComment());
        }
        return response;
    }

}
