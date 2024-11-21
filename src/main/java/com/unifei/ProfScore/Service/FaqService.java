package com.unifei.ProfScore.Service;

import com.unifei.ProfScore.dto.Faq.FaqCreateDTO;
import com.unifei.ProfScore.dto.Faq.FaqResponseDTO;
import com.unifei.ProfScore.dto.Faq.FaqUpdateDTO;
import com.unifei.ProfScore.model.Faq;

import java.util.List;

public interface FaqService {

    Faq getById(int id);

    List<Faq> getAll();

    Faq create(FaqCreateDTO faqCreateDTO);

    Faq update(int id, FaqUpdateDTO faqUpdateDTO);

    void delete(int id);

    FaqResponseDTO getFaqResponseDTO(Faq faq);
}
