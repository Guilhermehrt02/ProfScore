package com.unifei.ProfScore.dto.Faq;

import com.unifei.ProfScore.model.Faq;
import lombok.Data;

@Data
public class FaqResponseDTO {
    private int id;
    private String title;
    private String content;

    // Construtor para facilitar a conversão
    public FaqResponseDTO(Faq faq) {
        this.id = faq.getId();
        this.title = faq.getTitle();
        this.content = faq.getContent();
    }
}
