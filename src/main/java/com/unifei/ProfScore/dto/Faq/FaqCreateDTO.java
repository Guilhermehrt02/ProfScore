package com.unifei.ProfScore.dto.Faq;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FaqCreateDTO {
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Content is mandatory")
    private String content;
}
