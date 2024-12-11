package com.unifei.ProfScore.Service;

import com.unifei.ProfScore.domain.Administrator;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    Administrator getById(int id);

    Optional<Administrator> getByEmail(String email);

    List<Administrator> getAll();

    Administrator create(Administrator administrator);

    Administrator update(int id, Administrator updatedAdministrator);

    void delete(int id);
}
