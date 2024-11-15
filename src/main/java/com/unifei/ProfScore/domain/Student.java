package com.unifei.ProfScore.domain;

import com.unifei.ProfScore.model.Course;
import com.unifei.ProfScore.model.Feedback;
import com.unifei.ProfScore.model.ProfessorRating;
import com.unifei.ProfScore.model.UniversityRating;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Getter
@NoArgsConstructor
public class Student extends User {

    @OneToMany(mappedBy = "student")
    private List<ProfessorRating> profRatings = new ArrayList<>();

    @OneToMany
    private List<UniversityRating> uniRatings = new ArrayList<>();

    @OneToMany
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToOne
    private Course course;

    private int entryYear;

    public Student(String name, String email, String password) {
        super(name, email, password);
        setId(0);
    }

}
