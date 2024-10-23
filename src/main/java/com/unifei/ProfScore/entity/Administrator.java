package com.unifei.ProfScore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Administrator extends User {

    @OneToMany
    private List<ReportModel> reports = new ArrayList<>();

    public Administrator(String name, String email, String password) {
        super(name, email, password);
    }

    public void generateReport() {

    }
}
