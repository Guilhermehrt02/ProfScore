package com.unifei.ProfScore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ReportModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

    public ReportModel(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
