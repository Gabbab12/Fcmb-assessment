package com.fcmb.gabriel.coresecuritystarter.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "security.jwt")
@Getter
@Setter
public class JwtProperties {

    private String secret;
    private Duration expiry;

}

