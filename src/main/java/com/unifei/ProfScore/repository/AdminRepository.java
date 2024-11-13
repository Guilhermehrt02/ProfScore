package com.unifei.ProfScore.repository;

import com.unifei.ProfScore.domain.Administrator;
import com.unifei.ProfScore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Administrator, Integer> {

}
