package com.unifei.ProfScore.Service.impl;

import com.unifei.ProfScore.Service.StudentService;
import com.unifei.ProfScore.repository.ProfessorRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorRatingServiceImpl {

    private ProfessorRatingRepository professorRatingRepository;
    private ProfessorService professorService;
    private StudentService studentService;

    @Autowired
    public ProfessorRatingServiceImpl(ProfessorRatingRepository professorRatingRepository, ProfessorService professorService, StudentService studentService) {
        this.professorRatingRepository = professorRatingRepository;
        this.professorService = professorService;
        this.studentService = studentService;
    }
}
