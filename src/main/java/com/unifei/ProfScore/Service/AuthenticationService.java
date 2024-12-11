package com.unifei.ProfScore.Service;

public interface AuthenticationService {

    boolean authenticate(String email, String password, String type);
}
