package com.unifei.ProfScore.dto.Course;

import com.unifei.ProfScore.model.Course;
import lombok.Data;

@Data
public class CourseResponseDTO {
    private int id;
    private String name;
    private String universityName;
    private String universityCity;

    // Construtor para facilitar a conversão
    public CourseResponseDTO(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        if (course.getUniversity() != null) {
            this.universityName = course.getUniversity().getName();
            this.universityCity = course.getUniversity().getCity();
        }
    }
}
