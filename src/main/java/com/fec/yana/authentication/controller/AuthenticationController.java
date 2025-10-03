package com.fec.yana.authentication.controller;

import com.fec.yana.authentication.constants.UrlPath;
import com.fec.yana.authentication.dto.requests.LoginRequest;
import com.fec.yana.authentication.dto.response.RegistrationResponse;
import com.fec.yana.authentication.dto.requests.RegistrationRequest;
import com.fec.yana.authentication.service.AuthenticationService;
import com.fec.yana.common.dto.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(UrlPath.AUTH)
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(UrlPath.LOGIN)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
        return this.authenticationService.login(loginRequest,  httpServletResponse);
    }

    @PostMapping(UrlPath.REGISTRATION)
    public BaseResponse<RegistrationResponse> registration(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return this.authenticationService.registration(registrationRequest);
    }

    @PostMapping(UrlPath.TOKEN_REFRESH)
    public ResponseEntity<?> tokenRefresh(HttpServletRequest request, HttpServletResponse response) {
        return this.authenticationService.tokenRefresh(request, response);
    }
}
