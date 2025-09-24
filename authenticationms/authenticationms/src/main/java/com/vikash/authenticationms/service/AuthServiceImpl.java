package com.vikash.authenticationms.service;

import com.vikash.authenticationms.dto.LoginDto;
import com.vikash.authenticationms.dto.LoginResponse;
import com.vikash.authenticationms.dto.RegisterDto;
import com.vikash.authenticationms.model.AuthUser;
import com.vikash.authenticationms.repository.AuthUserRepository;
import com.vikash.authenticationms.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public String register(RegisterDto registerDto) {
        boolean userExists=authUserRepository.existsByEmail(registerDto.getEmail());
        if(userExists){
            return "User already exists with this email : "+registerDto.getEmail();
        }else {
            AuthUser authUser=new AuthUser();
            authUser.setId(null);
            authUser.setEmail(registerDto.getEmail());
            authUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            authUser.setRole(registerDto.getRole());
            authUserRepository.save(authUser);
            return "User created successfully";
        }
    }

    @Override
    public LoginResponse login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthentication=
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                        loginDto.getPassword());
        Authentication authentication=authenticationManager
                .authenticate(usernamePasswordAuthentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtUtils.generateToken((UserDetails) authentication.getPrincipal());
        LoginResponse loginResponse=new LoginResponse();
        loginResponse.setUsername(((UserDetails) authentication.getPrincipal())
                .getUsername());
        loginResponse.setRole(authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList().get(0));

        loginResponse.setToken(token);
        return loginResponse;
    }

}
