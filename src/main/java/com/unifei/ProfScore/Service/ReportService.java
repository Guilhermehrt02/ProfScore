package com.unifei.ProfScore.Service;

import com.unifei.ProfScore.dto.Report.ReportCreateDTO;
import com.unifei.ProfScore.dto.Report.ReportResponseDTO;
import com.unifei.ProfScore.dto.Report.ReportUpdateDTO;
import com.unifei.ProfScore.model.ReportModel;

import java.util.List;

public interface ReportService {

    ReportModel getById(int id);

    List<ReportModel> getAll();

    ReportModel create(ReportCreateDTO courseCreateDTO);

    ReportModel update(int id, ReportUpdateDTO courseUpdateDTO);

    void delete(int id);

    ReportResponseDTO getReportResponseDTO(ReportModel course);
}
