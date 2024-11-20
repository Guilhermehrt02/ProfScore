package com.unifei.ProfScore.Service.impl;

import com.unifei.ProfScore.Service.AdminService;
import com.unifei.ProfScore.domain.Administrator;
import com.unifei.ProfScore.repository.AdminRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Administrator getById(int id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Administrator not found with id - " + id));
    }

    @Override
    public List<Administrator> getAll() {
        return adminRepository.findAll();
    }

    @Override
    public Administrator create(Administrator administrator) {
        if (adminRepository.findByEmail(administrator.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered - " + administrator.getEmail());
        }

        administrator.setId(0);
        return adminRepository.save(administrator);
    }

    @Override
    public Administrator update(int id, Administrator updatedAdministrator) {
        Administrator existingAdministrator = getById(id);

        if (updatedAdministrator.getEmail() != null) {
            Optional<Administrator> result = adminRepository.findByEmail(updatedAdministrator.getEmail());

            if (result.isPresent() && result.get().getId() != id) {
                throw new IllegalArgumentException("Email already registered - " + updatedAdministrator.getEmail());
            }

            existingAdministrator.setEmail(updatedAdministrator.getEmail());
        }

        if (updatedAdministrator.getName() != null) {
            existingAdministrator.setName(updatedAdministrator.getName());
        }

        if (updatedAdministrator.getPassword() != null) {
            existingAdministrator.setPassword(updatedAdministrator.getPassword());
        }

        return adminRepository.save(existingAdministrator);
    }

    @Override
    public void delete(int id) {
        Administrator administrator = getById(id);
        adminRepository.delete(administrator);
    }
}
