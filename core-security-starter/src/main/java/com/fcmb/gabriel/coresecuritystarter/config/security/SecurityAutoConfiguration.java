package com.fcmb.gabriel.coresecuritystarter.config.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
@ConditionalOnClass(SecurityFilterChain.class)
public class SecurityAutoConfiguration {

    @Bean
    public JwtTokenProvider jwtTokenProvider(JwtProperties props) {
        return new JwtTokenProvider(props);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
            JwtTokenProvider tokenProvider) {
        return new JwtAuthenticationFilter(tokenProvider);
    }
}

