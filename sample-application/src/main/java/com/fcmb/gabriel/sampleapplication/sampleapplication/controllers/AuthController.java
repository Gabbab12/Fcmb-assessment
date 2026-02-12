package com.fcmb.gabriel.sampleapplication.sampleapplication.controllers;

import com.fcmb.gabriel.coresecuritystarter.config.security.JwtTokenProvider;
import com.fcmb.gabriel.coresecuritystarter.dtos.requests.LoginRequest;
import com.fcmb.gabriel.sampleapplication.sampleapplication.services.authentication.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final LoginService loginService;

    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {
        return loginService.login(request);
    }
}

