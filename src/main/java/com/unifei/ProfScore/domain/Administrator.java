package com.unifei.ProfScore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unifei.ProfScore.model.ReportModel;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Administrator extends User {

    @OneToMany
    @JsonIgnore
    private List<ReportModel> reports = new ArrayList<>();

    public Administrator(String name, String email, String password) {
        super(name, email, password);
    }

    public void generateReport() {

    }
}
