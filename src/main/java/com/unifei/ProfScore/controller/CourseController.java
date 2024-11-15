package com.unifei.ProfScore.controller;

import com.unifei.ProfScore.Service.CourseService;
import com.unifei.ProfScore.dto.Course.CourseCreateDTO;
import com.unifei.ProfScore.dto.Course.CourseResponseDTO;
import com.unifei.ProfScore.dto.Course.CourseUpdateDTO;
import com.unifei.ProfScore.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<CourseResponseDTO>> findAll() {
        List<Course> courses = courseService.getAll();

        // Converte cada Course em CourseResponseDTO
        List<CourseResponseDTO> courseResponseDTOs = courses.stream()
                .map(CourseResponseDTO::new) // Construtor no DTO para conversão
                .toList();

        return ResponseEntity.ok(courseResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable int id) {

        Course course = courseService.getById(id);

        CourseResponseDTO courseResponseDTO = courseService.getCourseResponseDTO(course);
        return ResponseEntity.ok(courseResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<?> registerCourse(@RequestBody CourseCreateDTO course) {

        Course newCourse = courseService.create(course);
        CourseResponseDTO courseResponseDTO = courseService.getCourseResponseDTO(newCourse);

        return ResponseEntity.status(HttpStatus.CREATED).body(courseResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable int id, @RequestBody CourseUpdateDTO updatedCourse) {

        Course course = courseService.update(id, updatedCourse);
        CourseResponseDTO courseResponseDTO = courseService.getCourseResponseDTO(course);

        return ResponseEntity.ok(courseResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {

        courseService.delete(id);

        return ResponseEntity.ok("Course has been successfully deleted.");
    }
}
