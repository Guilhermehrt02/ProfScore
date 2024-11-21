package com.unifei.ProfScore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    private float score;

    @OneToMany(mappedBy = "professor")
    private List<ProfessorRating> rating = new ArrayList<>();

    public Professor(String name) {
        this.name = name;
    }
}
