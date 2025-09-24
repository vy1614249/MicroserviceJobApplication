package com.vikash.authenticationms.controller;

import com.vikash.authenticationms.dto.LoginDto;
import com.vikash.authenticationms.dto.LoginResponse;
import com.vikash.authenticationms.dto.RegisterDto;
import com.vikash.authenticationms.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginDto loginDto){
        return authService.login(loginDto);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterDto registerDto){
        return authService.register(registerDto);
    }
}
