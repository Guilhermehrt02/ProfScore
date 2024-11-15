package com.unifei.ProfScore.Service;

import com.unifei.ProfScore.model.Course;
import com.unifei.ProfScore.model.University;
import com.unifei.ProfScore.repository.CourseRepository;
import com.unifei.ProfScore.repository.UniversityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    public University getById(int id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("University not found with id - " + id));
    }

    public List<University> getAll() {
        return universityRepository.findAll();
    }

    public University create(University university) {
        university.setId(0);
        return universityRepository.save(university);
    }

    public University update(int id, University updatedUniversity) {

        University existingUniversity = getById(id);

        if (updatedUniversity.getName() != null)
            existingUniversity.setName(updatedUniversity.getName());

        if (updatedUniversity.getCity() != null)
            existingUniversity.setCity(updatedUniversity.getCity());

        return universityRepository.save(existingUniversity);
    }

    public void delete(int id) {
        University university = getById(id);
        universityRepository.delete(university);
    }
}
