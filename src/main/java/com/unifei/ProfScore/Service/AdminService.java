package com.unifei.ProfScore.Service;

import com.unifei.ProfScore.domain.Administrator;
import com.unifei.ProfScore.repository.AdminRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService{

    @Autowired
    private AdminRepository adminRepository;

    public Administrator getById(int id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Administrator not found with id - " + id));
    }

    public List<Administrator> getAll() {
        return adminRepository.findAll();
    }

    public Administrator create(Administrator administrator) {

        if (adminRepository.findByEmail(administrator.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered - " + administrator.getEmail());
        }

        administrator.setId(0);
        return adminRepository.save(administrator);
    }

    public Administrator update(int id, Administrator updatedAdministrator) {

        //verify if the administrator exists
        Administrator existingAdministrator = getById(id);

        //verify if the email provided is associated with another administrator
        if (updatedAdministrator.getEmail() != null) {

            Optional<Administrator> result = adminRepository.findByEmail(updatedAdministrator.getEmail());

            if (result.isPresent() && result.get().getId() != id) {
                throw new IllegalArgumentException("Email already registered - " + updatedAdministrator.getEmail());
            }

            existingAdministrator.setEmail(updatedAdministrator.getEmail());
        }

        if (updatedAdministrator.getName() != null)
            existingAdministrator.setName(updatedAdministrator.getName());

        if (updatedAdministrator.getPassword() != null)
            existingAdministrator.setPassword(updatedAdministrator.getPassword());

        return adminRepository.save(existingAdministrator);
    }

    public void delete(int id) {
        Administrator administrator = getById(id);
        adminRepository.delete(administrator);
    }
}
