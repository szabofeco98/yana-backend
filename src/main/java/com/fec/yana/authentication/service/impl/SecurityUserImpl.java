package com.fec.yana.authentication.service.impl;

import com.fec.yana.authentication.dto.exceptions.UsernameAlreadyExistsException;
import com.fec.yana.authentication.dto.response.RegistrationResponse;
import com.fec.yana.authentication.dto.requests.RegistrationRequest;
import com.fec.yana.authentication.mapper.entity.SecurityUserMapper;
import com.fec.yana.authentication.mapper.response.RegistrationResponseMapper;
import com.fec.yana.authentication.model.SecurityUser;
import com.fec.yana.authentication.repository.SecurityUserRepository;
import com.fec.yana.authentication.service.SecurityUserService;
import com.fec.yana.common.dto.response.BaseResponse;
import com.fec.yana.common.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserImpl extends BaseService implements SecurityUserService {
    private final SecurityUserRepository securityUserRepository;
    private final RegistrationResponseMapper registrationResponseMapper;
    private final SecurityUserMapper securityUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public BaseResponse<RegistrationResponse> registration(RegistrationRequest registrationRequest) {
        if (this.securityUserRepository.existsSecurityUserByUsername(registrationRequest.username())) {
            throw new UsernameAlreadyExistsException();
        }

        String encodedPassword = passwordEncoder.encode(registrationRequest.password());
        SecurityUser securityUser = this.securityUserMapper.toSecurityUser(registrationRequest, encodedPassword);
        return this.executeDbOperation(() -> this.securityUserRepository.save(securityUser), this.registrationResponseMapper::toRegistrationResponse);
    }
}
