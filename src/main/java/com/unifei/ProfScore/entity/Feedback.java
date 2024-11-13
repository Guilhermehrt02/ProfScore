package com.unifei.ProfScore.entity;

import com.unifei.ProfScore.domain.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String feedback;

    @ManyToOne
    private Student student;

    public Feedback(String feedback, Student student) {
        this.feedback = feedback;
        this.student = student;
    }
}
