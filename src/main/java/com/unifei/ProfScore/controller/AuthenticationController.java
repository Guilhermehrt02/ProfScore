package com.unifei.ProfScore.controller;

import com.unifei.ProfScore.Service.AuthenticationService;
import com.unifei.ProfScore.Service.StudentService;
import com.unifei.ProfScore.dto.Auth.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginDTO loginDTO) {

        return ResponseEntity.ok(authenticationService.authenticate(loginDTO.getEmail(),
                                                                    loginDTO.getPassword(),
                                                                    loginDTO.getType()));
    }

}
