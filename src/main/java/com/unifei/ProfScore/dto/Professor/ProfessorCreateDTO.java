package com.unifei.ProfScore.dto.Professor;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfessorCreateDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;
}
