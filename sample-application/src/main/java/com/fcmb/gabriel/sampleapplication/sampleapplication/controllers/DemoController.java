package com.fcmb.gabriel.sampleapplication.sampleapplication.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/public/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/user/me")
    public String me(Authentication auth) {
        return auth.getName();
    }

    @GetMapping("/admin/users")
    public String admin() {
        return "admin-only";
    }
}

