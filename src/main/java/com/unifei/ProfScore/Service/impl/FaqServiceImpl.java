package com.unifei.ProfScore.Service.impl;

import com.unifei.ProfScore.Service.FaqService;
import com.unifei.ProfScore.dto.Faq.FaqCreateDTO;
import com.unifei.ProfScore.dto.Faq.FaqResponseDTO;
import com.unifei.ProfScore.dto.Faq.FaqUpdateDTO;
import com.unifei.ProfScore.model.Faq;
import com.unifei.ProfScore.repository.FaqRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqServiceImpl implements FaqService {

    private final FaqRepository faqRepository;

    @Autowired
    public FaqServiceImpl(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @Override
    public Faq getById(int id) {
        return faqRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Faq not found with id - " + id));
    }

    @Override
    public List<Faq> getAll() {
        return faqRepository.findAll();
    }

    @Override
    public Faq create(FaqCreateDTO faqCreateDTO) {
        Faq faq = faqRepository.findByTitle(faqCreateDTO.getTitle())
                .orElseThrow(() -> new IllegalArgumentException(
                "Faq not found: " + faqCreateDTO.getTitle()));

        if (faqRepository.findByTitle(faqCreateDTO.getTitle()).isPresent()) {
            throw new IllegalArgumentException("Faq already exists: " + faqCreateDTO.getTitle());
        }

        Faq newFaq = new Faq();
        newFaq.setTitle(faqCreateDTO.getTitle());
        newFaq.setContent(faqCreateDTO.getContent());

        return faqRepository.save(newFaq);
    }

    // TODO: Check me
    @Override
    public Faq update(int id, FaqUpdateDTO faqUpdateDTO) {
        Faq existingFaq = getById(id);

        if (faqUpdateDTO.getTitle() != null && !faqUpdateDTO.getContent().isBlank()) {
            existingFaq.setTitle(faqUpdateDTO.getTitle());
            existingFaq.setContent(faqUpdateDTO.getContent());

            if (faqRepository.findByTitle(faqUpdateDTO.getTitle()).isPresent()) {
                throw new IllegalArgumentException("Faq already exists: " + faqUpdateDTO.getTitle());
            }
        }

        return faqRepository.save(existingFaq);
    }

    @Override
    public void delete(int id) {
        Faq faq = getById(id);
        faqRepository.delete(faq);
    }

    @Override
    public FaqResponseDTO getFaqResponseDTO(Faq faq) {
        return new FaqResponseDTO(faq);
    }
}
