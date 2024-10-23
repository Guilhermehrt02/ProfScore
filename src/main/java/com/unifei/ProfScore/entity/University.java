package com.unifei.ProfScore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class University {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    private float score;

    private String city;

    @OneToMany(mappedBy = "university")
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "university")
    private List<UniversityRating> rating = new ArrayList<>();

    public University(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
