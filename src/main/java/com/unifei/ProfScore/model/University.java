package com.unifei.ProfScore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(
        name = "university",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "city"})
)
public class University {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    private float score;

    private String city;

    @OneToMany(mappedBy = "university")
    @JsonIgnore // Evitar loop infinito
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "university")
    @JsonIgnore
    private List<UniversityRating> rating = new ArrayList<>();

    public University(String name, String city) {
        this.name = name;
        this.city = city;
    }

}
