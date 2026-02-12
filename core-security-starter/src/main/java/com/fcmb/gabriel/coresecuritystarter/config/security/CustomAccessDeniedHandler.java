package com.fcmb.gabriel.coresecuritystarter.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fcmb.gabriel.coresecuritystarter.dtos.responses.ApiResponse;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) throws IOException {
        String requestPathUrl = request.getRequestURL().toString();
        log.error("endpoint request was sent to is : {}", requestPathUrl);
        String requestURI = request.getRequestURI();
        log.error("request uri was sent to is : {}", requestURI);
        log.error("CustomAccessDeniedHandler Forbidden error: {}", exc.getMessage());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ServletOutputStream out = response.getOutputStream();
        new ObjectMapper().writeValue(out, new ApiResponse<>(false, "FORBIDDEN: You are not authorized to access this resource", null));
        out.flush();
    }
}
