package com.unifei.ProfScore.controller;

import com.unifei.ProfScore.Service.FeedbackService;
import com.unifei.ProfScore.dto.Feedback.FeedbackDTO;
import com.unifei.ProfScore.dto.Feedback.FeedbackResponseDTO;
import com.unifei.ProfScore.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/all")
    public ResponseEntity<List<FeedbackResponseDTO>> findAll() {

        List<FeedbackResponseDTO> feedbacks = feedbackService.getAll()
                .stream().map(feedback -> feedbackService.getFeedbackResponseDTO(feedback)).toList();;

        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponseDTO> getFeedbackById(@PathVariable int id) {

        Feedback feedback = feedbackService.getById(id);

        return ResponseEntity.ok(feedbackService.getFeedbackResponseDTO(feedback));
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<FeedbackResponseDTO>> getFeedbackByStudent(@PathVariable int id) {

        List<FeedbackResponseDTO> feedbacks = feedbackService.getByStudent(id)
                .stream().map(feedback -> feedbackService.getFeedbackResponseDTO(feedback))
                .toList();

        return ResponseEntity.ok(feedbacks);
    }

    @PostMapping()
    public ResponseEntity<?> registerFeedback(@RequestBody FeedbackDTO feedback) {

        Feedback newFeedback = feedbackService.create(feedback);

        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackService.getFeedbackResponseDTO(newFeedback));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackResponseDTO> updateFeedback(@PathVariable int id, @RequestBody FeedbackDTO updatedFeedback) {

        Feedback feedback = feedbackService.update(id, updatedFeedback);

        return ResponseEntity.ok(feedbackService.getFeedbackResponseDTO(feedback));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable int id) {

        feedbackService.delete(id);

        return ResponseEntity.ok("Feedback has been successfully deleted.");
    }
}
