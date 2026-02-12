package com.fcmb.gabriel.coresecuritystarter.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fcmb.gabriel.coresecuritystarter.dtos.responses.ApiResponse;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint
        implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String requestPathUrl = request.getRequestURL().toString();
        log.error("endpoint request was sent to is : {}", requestPathUrl);
        log.error("CustomAuthenticationEntryPoint Unauthorized error: {}", authException.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ServletOutputStream out = response.getOutputStream();
        new ObjectMapper().writeValue(out, new ApiResponse<>(false, "UNAUTHORIZED: Kindly login before attempting to access this resource", null));
        out.flush();
    }

}
