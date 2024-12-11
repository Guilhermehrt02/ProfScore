package com.unifei.ProfScore.dto.Auth;

import lombok.Data;

@Data
public class LoginDTO {

    private String email;
    private String password;
    private String type; //student ou admin
}
