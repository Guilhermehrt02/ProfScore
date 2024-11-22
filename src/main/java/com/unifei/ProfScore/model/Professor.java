package com.unifei.ProfScore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private int totalRatings = 0;

    private int sumRatings = 0;

    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    private List<ProfessorRating> rating = new ArrayList<>();

    public Professor(String name) {
        this.name = name;
    }
}
