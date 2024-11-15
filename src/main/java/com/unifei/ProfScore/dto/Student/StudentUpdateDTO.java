package com.unifei.ProfScore.dto.Student;

import lombok.Data;

@Data
public class StudentUpdateDTO {
    private String name;
    private String email;
    private String password;
    private Integer entryYear;
    private String courseName;
    private String universityName;

}
