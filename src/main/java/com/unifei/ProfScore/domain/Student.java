package com.unifei.ProfScore.domain;

import com.unifei.ProfScore.entity.Feedback;
import com.unifei.ProfScore.entity.ProfessorRating;
import com.unifei.ProfScore.entity.UniversityRating;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Student extends User {

    @OneToMany(mappedBy = "student")
    private List<ProfessorRating> profRatings = new ArrayList<>();

    @OneToMany
    private List<UniversityRating> uniRatings = new ArrayList<>();

    @OneToMany
    private List<Feedback> feedbacks = new ArrayList<>();

    public Student(String name, String email, String password) {
        super(name, email, password);
    }

}
