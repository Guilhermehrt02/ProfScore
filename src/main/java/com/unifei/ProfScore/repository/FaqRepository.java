package com.unifei.ProfScore.repository;

import com.unifei.ProfScore.model.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FaqRepository extends JpaRepository<Faq, Integer> {

    Optional<Faq> findByTitle(String title);
}
