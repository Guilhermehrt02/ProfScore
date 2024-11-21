package com.unifei.ProfScore.dto.Report;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReportCreateDTO {
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Content name is mandatory")
    private String content;
}
