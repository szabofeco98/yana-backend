package com.fec.yana.authentication.service;

import com.fec.yana.authentication.dto.response.RegistrationResponse;
import com.fec.yana.authentication.dto.requests.RegistrationRequest;
import com.fec.yana.common.dto.response.BaseResponse;

public interface SecurityUserService {
    BaseResponse<RegistrationResponse> registration(RegistrationRequest registrationRequest);
}
