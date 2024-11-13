package com.unifei.ProfScore.domain;

import com.unifei.ProfScore.entity.ReportModel;
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
    private List<ReportModel> reports = new ArrayList<>();

    public Administrator(String name, String email, String password) {
        super(name, email, password);
    }

    public void generateReport() {

    }
}
