package com.unifei.ProfScore.model;

import com.unifei.ProfScore.domain.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UniversityRating {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String comment;

    private int rating;

    @ManyToOne
    private Student student;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    public UniversityRating(int rating, Student student, University university) {
        this.rating = rating;
        this.student = student;
        this.university = university;
    }
}
