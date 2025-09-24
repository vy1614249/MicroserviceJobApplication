package com.vikash.authenticationms.dto;

import com.vikash.authenticationms.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @Size(min = 8,max = 20)
    private String password;

    private Role role;
}
