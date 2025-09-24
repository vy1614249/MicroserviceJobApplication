package com.vikash.authenticationms.dto;

import com.vikash.authenticationms.model.Role;
import lombok.Data;

@Data
public class LoginResponse {
    private String username;
    private String role;
    private String token;
}