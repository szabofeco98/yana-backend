package com.fec.yana.authentication.controller;

import com.fec.yana.authentication.constants.UrlPath;
import com.fec.yana.authentication.dto.response.RegistrationResponse;
import com.fec.yana.authentication.dto.response.LoginResponse;
import com.fec.yana.authentication.dto.requests.RegistrationRequest;
import com.fec.yana.authentication.service.SecurityUserService;
import com.fec.yana.common.dto.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(UrlPath.AUTH)
@RequiredArgsConstructor
public class AuthenticationController {
    private final SecurityUserService securityUserService;

    @PostMapping(UrlPath.LOGIN)
    public LoginResponse login() {
        return new LoginResponse("teszt", "session");
    }

    @PostMapping(UrlPath.REGISTRATION)
    public BaseResponse<RegistrationResponse> registration(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return this.securityUserService.registration(registrationRequest);
    }
}
