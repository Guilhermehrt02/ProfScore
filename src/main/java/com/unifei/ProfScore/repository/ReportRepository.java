package com.unifei.ProfScore.repository;

import com.unifei.ProfScore.model.ReportModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ReportRepository extends JpaRepository<ReportModel, Integer> {

    Optional<ReportModel> findByTitle(String title);
}
