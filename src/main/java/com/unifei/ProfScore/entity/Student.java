package com.unifei.ProfScore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student extends User{

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
