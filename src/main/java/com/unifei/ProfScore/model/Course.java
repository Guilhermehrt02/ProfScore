package com.unifei.ProfScore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@Table(
        name = "course",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "university_id"})
)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    public Course(String name, University university) {
        this.name = name;
        this.university = university;
    }

}
