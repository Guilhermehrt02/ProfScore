package com.unifei.ProfScore.Service.impl;

import com.unifei.ProfScore.Service.AdminService;
import com.unifei.ProfScore.Service.AuthenticationService;
import com.unifei.ProfScore.Service.StudentService;
import com.unifei.ProfScore.domain.Administrator;
import com.unifei.ProfScore.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private StudentService studentService;
    private AdminService adminService;

    @Autowired
    public AuthenticationServiceImpl(StudentService studentService, AdminService adminService) {
        this.studentService = studentService;
        this.adminService = adminService;
    }

    @Override
    public boolean authenticate(String email, String password, String type) {
        if (type.equals("student")) {
            Optional<Student> student = studentService.getByEmail(email);

            if (!student.isPresent()) {
                return false;
            }

            if(student.get().getPassword().equals(password)){
                return true;
            }

        } else if (type.equals("admin")) {

            Optional<Administrator> admin = adminService.getByEmail(email);

            if (!admin.isPresent()) {
                return false;
            }

            if (admin.get().getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
