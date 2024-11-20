package com.unifei.ProfScore.Service;

import com.unifei.ProfScore.model.University;

import java.util.List;

public interface UniversityService {
    University getById(int id);

    List<University> getAll();

    University create(University university);

    University update(int id, University updatedUniversity);

    void delete(int id);
}
