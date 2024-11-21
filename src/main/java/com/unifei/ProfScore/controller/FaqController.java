package com.unifei.ProfScore.controller;

import com.unifei.ProfScore.Service.FaqService;
import com.unifei.ProfScore.dto.Faq.FaqCreateDTO;
import com.unifei.ProfScore.dto.Faq.FaqUpdateDTO;
import com.unifei.ProfScore.model.Faq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faq")
public class FaqController {

    @Autowired
    private FaqService faqService;

    @GetMapping("/all")
    public ResponseEntity<List<Faq>> findAll() {
        List<Faq> faq = faqService.getAll();
        return ResponseEntity.ok(faq);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faq> getFaqById(@PathVariable int id) {

        Faq faq = faqService.getById(id);

        return ResponseEntity.ok(faq);
    }

    @PostMapping()
    public ResponseEntity<?> registerFaq(@RequestBody Faq faq) {

        FaqCreateDTO faqCreateDTO = new FaqCreateDTO();
        faqCreateDTO.setTitle(faq.getTitle());
        faqCreateDTO.setContent(faq.getContent());
        Faq newFaq = faqService.create(faqCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFaq);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faq> update(@PathVariable int id, @RequestBody Faq updatedFaq) {
        FaqUpdateDTO faqUpdateDTO = new FaqUpdateDTO();
        faqUpdateDTO.setTitle(updatedFaq.getTitle());
        faqUpdateDTO.setContent(updatedFaq.getContent());
        Faq faq = faqService.update(id, faqUpdateDTO);

        return ResponseEntity.ok(faq);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFaq(@PathVariable int id) {

        faqService.delete(id);

        return ResponseEntity.ok("Faq has been successfully deleted.");
    }
}
