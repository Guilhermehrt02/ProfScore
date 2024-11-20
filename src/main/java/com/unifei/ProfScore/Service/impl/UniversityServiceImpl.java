package com.unifei.ProfScore.Service.impl;

import com.unifei.ProfScore.Service.UniversityService;
import com.unifei.ProfScore.model.University;
import com.unifei.ProfScore.repository.UniversityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University getById(int id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("University not found with id - " + id));
    }

    @Override
    public List<University> getAll() {
        return universityRepository.findAll();
    }

    @Override
    public University create(University university) {
        university.setId(0); // Garante que será um novo registro
        return universityRepository.save(university);
    }

    @Override
    public University update(int id, University updatedUniversity) {
        University existingUniversity = getById(id);

        if (updatedUniversity.getName() != null) {
            existingUniversity.setName(updatedUniversity.getName());
        }

        if (updatedUniversity.getCity() != null) {
            existingUniversity.setCity(updatedUniversity.getCity());
        }

        return universityRepository.save(existingUniversity);
    }

    @Override
    public void delete(int id) {
        University university = getById(id);
        universityRepository.delete(university);
    }
}
