package com.unifei.ProfScore.Service.impl;

import com.unifei.ProfScore.Service.FeedbackService;
import com.unifei.ProfScore.Service.StudentService;
import com.unifei.ProfScore.domain.Student;
import com.unifei.ProfScore.dto.Feedback.FeedbackDTO;
import com.unifei.ProfScore.dto.Feedback.FeedbackResponseDTO;
import com.unifei.ProfScore.model.Feedback;
import com.unifei.ProfScore.repository.FeedbackRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepository feedbackRepository;
    private StudentService studentService;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, StudentService studentService) {
        this.feedbackRepository = feedbackRepository;
        this.studentService = studentService;
    }

    @Override
    public Feedback getById(int id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feedback not found with id - " + id));
    }

    @Override
    public List<Feedback> getByStudent(int id) {

        Student student = studentService.getById(id);

        return feedbackRepository.findByStudentId(student.getId());
    }

    @Override
    public List<Feedback> getAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback create(FeedbackDTO feedbackDTO) {

        Student student = studentService.getById(feedbackDTO.getStudentId());

        Feedback feedback = new Feedback(feedbackDTO.getFeedback(), student);

        return feedbackRepository.save(feedback);
    }


    @Override
    public Feedback update(int id, FeedbackDTO feedbackDTO) {
        Feedback existingFeedback = getById(id);

        if (feedbackDTO.getFeedback() != null) {
            existingFeedback.setFeedback(feedbackDTO.getFeedback());
        }

        return feedbackRepository.save(existingFeedback);
    }

    @Override
    public void delete(int id) {
        Feedback feedback = getById(id);
        feedbackRepository.delete(feedback);
    }

    @Override
    public FeedbackResponseDTO getFeedbackResponseDTO(Feedback feedback) {
        return new FeedbackResponseDTO(feedback.getId(), feedback.getFeedback(), feedback.getStudent().getId());
    }

}
