package com.unifei.ProfScore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    private String semester;

    @ManyToOne // Adicione esta anotação
    @JoinColumn(name = "university_id")
    private University university;

    public Course(String name, String semester, University university) {
        this.name = name;
        this.semester = semester;
        this.university = university;
    }
}
