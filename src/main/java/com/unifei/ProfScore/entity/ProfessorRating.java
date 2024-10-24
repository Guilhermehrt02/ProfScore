package com.unifei.ProfScore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProfessorRating {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String comment;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Professor professor;

    public ProfessorRating(String comment, Student student, Professor professor) {
        this.comment = comment;
        this.student = student;
        this.professor = professor;
    }
}
