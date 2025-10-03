package com.fec.yana.authentication.service;

import com.fec.yana.authentication.dto.requests.LoginRequest;
import com.fec.yana.authentication.dto.response.RegistrationResponse;
import com.fec.yana.authentication.dto.requests.RegistrationRequest;
import com.fec.yana.common.dto.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    BaseResponse<RegistrationResponse> registration(RegistrationRequest registrationRequest);

    ResponseEntity<?> login(LoginRequest loginRequest, HttpServletResponse response);

    ResponseEntity<?> tokenRefresh(HttpServletRequest request, HttpServletResponse  response);
}
