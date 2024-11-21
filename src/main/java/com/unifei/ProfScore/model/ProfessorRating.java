package com.unifei.ProfScore.model;

import com.unifei.ProfScore.domain.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class ProfessorRating {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String comment;

    private int rating;

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
