package com.unifei.ProfScore.dto.Student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class StudentCreateDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Entry year is mandatory")
    private Integer entryYear;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private String courseName; // Opcional, para associar a um curso
    private String universityName; // Opcional, para associar à universidade
}
