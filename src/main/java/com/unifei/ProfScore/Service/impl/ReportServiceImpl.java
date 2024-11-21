package com.unifei.ProfScore.Service.impl;

import com.unifei.ProfScore.Service.ReportService;
import com.unifei.ProfScore.dto.Report.ReportCreateDTO;
import com.unifei.ProfScore.dto.Report.ReportResponseDTO;
import com.unifei.ProfScore.dto.Report.ReportUpdateDTO;
import com.unifei.ProfScore.model.ReportModel;
import com.unifei.ProfScore.repository.ReportRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public ReportModel getById(int id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report not found with id - " + id));
    }

    @Override
    public List<ReportModel> getAll() {
        return reportRepository.findAll();
    }

    @Override
    public ReportModel create(ReportCreateDTO reportCreateDTO) {
        ReportModel report = reportRepository.findByTitle(reportCreateDTO.getTitle())
                .orElseThrow(() -> new IllegalArgumentException(
                "Report not found: " + reportCreateDTO.getTitle()));

        if (reportRepository.findByTitle(reportCreateDTO.getTitle()).isPresent()) {
            throw new IllegalArgumentException("Report already exists: " + reportCreateDTO.getTitle());
        }

        ReportModel newReport = new ReportModel();
        newReport.setTitle(reportCreateDTO.getTitle());
        newReport.setContent(reportCreateDTO.getContent());

        return reportRepository.save(newReport);
    }

    // TODO: Check me
    @Override
    public ReportModel update(int id, ReportUpdateDTO reportUpdateDTO) {
        ReportModel existingReport = getById(id);

        if (reportUpdateDTO.getTitle() != null && !reportUpdateDTO.getContent().isBlank()) {
            existingReport.setTitle(reportUpdateDTO.getTitle());
            existingReport.setContent(reportUpdateDTO.getContent());

            if (reportRepository.findByTitle(reportUpdateDTO.getTitle()).isPresent()) {
                throw new IllegalArgumentException("Report already exists: " + reportUpdateDTO.getTitle());
            }
        }

        return reportRepository.save(existingReport);
    }

    @Override
    public void delete(int id) {
        ReportModel report = getById(id);
        reportRepository.delete(report);
    }

    @Override
    public ReportResponseDTO getReportResponseDTO(ReportModel report) {
        return new ReportResponseDTO(report);
    }
}
