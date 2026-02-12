package com.fcmb.gabriel.sampleapplication.sampleapplication.listeners;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomServletRequestListener implements ServletRequestListener {

    @EventListener
    public void onLogin(AuthenticationSuccessEvent event) {
        log.info("event.getAuthentication().getName() :{}", event.getAuthentication().getName());
        log.info("event.getAuthentication().isAuthenticated() :{}", event.getAuthentication().isAuthenticated());
    }

}
