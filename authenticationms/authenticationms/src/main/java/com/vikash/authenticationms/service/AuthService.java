package com.vikash.authenticationms.service;

import com.vikash.authenticationms.dto.LoginDto;
import com.vikash.authenticationms.dto.LoginResponse;
import com.vikash.authenticationms.dto.RegisterDto;

public interface AuthService {
     String register(RegisterDto registerDto);
     LoginResponse login(LoginDto loginDto );

}
