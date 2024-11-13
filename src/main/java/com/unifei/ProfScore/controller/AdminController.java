package com.unifei.ProfScore.controller;

import com.unifei.ProfScore.Service.AdminService;
import com.unifei.ProfScore.domain.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/all")
    public List<Administrator> getAll() {
        return adminService.getAll();
    }

    @GetMapping("/{email}")
    public Administrator getByEmail(@PathVariable String email) {
        return adminService.getId(Integer.valueOf(email)).orElse(null);
    }

    @PostMapping
    public Administrator postAdmin(@RequestBody Administrator admin) {
        return adminService.create(admin);
    }

    @PutMapping("/{email}")
    public Administrator putAdmin(@PathVariable String email, @RequestBody Administrator admin) {
        return adminService.update(admin);
    }

    @DeleteMapping("/{email}")
    public void deleteAdmin(@PathVariable String email) {
        adminService.deleteId(Integer.valueOf(email));
    }
}
