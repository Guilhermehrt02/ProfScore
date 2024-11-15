package com.unifei.ProfScore.dto.Course;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseCreateDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "University name is mandatory")
    private String universityName;
    @NotBlank(message = "University city is mandatory")
    private String universityCity;
}
