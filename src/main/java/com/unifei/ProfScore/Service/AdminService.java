package com.unifei.ProfScore.Service;

import com.unifei.ProfScore.domain.Administrator;

import java.util.List;

public interface AdminService {

    Administrator getById(int id);

    List<Administrator> getAll();

    Administrator create(Administrator administrator);

    Administrator update(int id, Administrator updatedAdministrator);

    void delete(int id);
}
