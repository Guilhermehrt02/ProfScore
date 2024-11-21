package com.unifei.ProfScore.dto.Report;

import com.unifei.ProfScore.model.ReportModel;
import lombok.Data;

@Data
public class ReportResponseDTO {
    private int id;
    private String title;
    private String content;

    // Construtor para facilitar a conversão
    public ReportResponseDTO(ReportModel reportModel) {
        this.id = reportModel.getId();
        this.title = reportModel.getTitle();
        this.content = reportModel.getContent();
    }
}
