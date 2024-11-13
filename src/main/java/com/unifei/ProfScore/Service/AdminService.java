package com.unifei.ProfScore.Service;

import com.unifei.ProfScore.domain.Administrator;
import com.unifei.ProfScore.repository.AdminRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService /*extends ServiceBase<Administrator, Integer, AdminRepository> */{

    @Autowired
    private AdminRepository adminRepository;

    public Administrator getById(int id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Administrator not found with id - " + id));
    }

    public List<Administrator> getAll() {
        return adminRepository.findAll();
    }

    public Administrator register(Administrator administrator) {

        if (adminRepository.findByEmail(administrator.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered - " + administrator.getEmail());
        }

        administrator.setId(0);
        return adminRepository.save(administrator);
    }

    public Administrator update(int id, Administrator updatedAdministrator) {

        Optional<Administrator> result = adminRepository.findByEmail(updatedAdministrator.getEmail());

        if (result.isPresent() && result.get().getId() != id) {
            throw new IllegalArgumentException("Email already registered - " + updatedAdministrator.getEmail());
        }

        Administrator existingAdministrator = getById(id);
        existingAdministrator.setName(updatedAdministrator.getName());
        existingAdministrator.setEmail(updatedAdministrator.getEmail());
        existingAdministrator.setPassword(updatedAdministrator.getPassword());

        return adminRepository.save(existingAdministrator);
    }

    public void delete(int id) {
        Administrator administrator = getById(id);
        adminRepository.delete(administrator);
    }
}
