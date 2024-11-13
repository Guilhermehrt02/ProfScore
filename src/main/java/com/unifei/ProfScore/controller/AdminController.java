package com.unifei.ProfScore.controller;

import com.unifei.ProfScore.Service.AdminService;
import com.unifei.ProfScore.domain.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/all")
    public ResponseEntity<List<Administrator>> findAll() {

        List<Administrator> administrators = adminService.getAll();

        return ResponseEntity.ok(administrators);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrator> getAdministratorById(@PathVariable int id) {

        Administrator administrator = adminService.getById(id);

        return ResponseEntity.ok(administrator);
    }

    @PostMapping()
    public ResponseEntity<Administrator> registerAdministrator(@RequestBody Administrator administrator) {

        Administrator newAdministrator = adminService.register(administrator);

        return ResponseEntity.status(HttpStatus.CREATED).body(newAdministrator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrator> updateAdministrator(@PathVariable int id, @RequestBody Administrator updatedAdministrator) {

        Administrator admin = adminService.update(id, updatedAdministrator);

        return ResponseEntity.ok(admin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdministrator(@PathVariable int id) {

        adminService.delete(id);

        return ResponseEntity.ok("Administrator has been successfully deleted.");
    }
}
