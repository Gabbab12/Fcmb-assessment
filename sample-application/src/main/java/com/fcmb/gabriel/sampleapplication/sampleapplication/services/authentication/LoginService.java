package com.fcmb.gabriel.sampleapplication.sampleapplication.services.authentication;

import com.fcmb.gabriel.coresecuritystarter.dtos.requests.LoginRequest;

import java.util.Map;

public interface LoginService {
    Map<String, Object> login(LoginRequest request);
}
