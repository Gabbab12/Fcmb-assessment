package com.fcmb.gabriel.sampleapplication.sampleapplication.services.authentication;

import com.fcmb.gabriel.coresecuritystarter.config.security.JwtTokenProvider;
import com.fcmb.gabriel.coresecuritystarter.dtos.requests.LoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Map<String, Object> login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        applicationEventPublisher.publishEvent(new AuthenticationSuccessEvent(auth));

        UserDetails user = (UserDetails) auth.getPrincipal();

        String token = jwtTokenProvider.generateToken(
                "1",
                user.getUsername(),
                user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()
        );

        return Map.of("token", token);
    }
}
