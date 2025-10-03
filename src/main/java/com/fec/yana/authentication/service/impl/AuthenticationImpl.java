package com.fec.yana.authentication.service.impl;

import com.fec.yana.authentication.dto.exceptions.UsernameAlreadyExistsException;
import com.fec.yana.authentication.dto.exceptions.WrongUsernameOrPasswordException;
import com.fec.yana.authentication.dto.requests.LoginRequest;
import com.fec.yana.authentication.dto.response.RegistrationResponse;
import com.fec.yana.authentication.dto.requests.RegistrationRequest;
import com.fec.yana.authentication.dto.util.Session;
import com.fec.yana.authentication.mapper.entity.SecurityUserMapper;
import com.fec.yana.authentication.mapper.response.RegistrationResponseMapper;
import com.fec.yana.authentication.model.SecurityUser;
import com.fec.yana.authentication.repository.SecurityUserRepository;
import com.fec.yana.authentication.service.AuthenticationService;
import com.fec.yana.authentication.utils.TokenUtil;
import com.fec.yana.common.dto.response.BaseResponse;
import com.fec.yana.common.service.BaseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationImpl extends BaseService implements AuthenticationService {
    private final SecurityUserRepository securityUserRepository;
    private final RegistrationResponseMapper registrationResponseMapper;
    private final SecurityUserMapper securityUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtil tokenUtil;

    @Override
    public BaseResponse<RegistrationResponse> registration(RegistrationRequest registrationRequest) {
        if (this.securityUserRepository.existsSecurityUserByUsername(registrationRequest.username())) {
            throw new UsernameAlreadyExistsException();
        }

        String encodedPassword = passwordEncoder.encode(registrationRequest.password());
        SecurityUser securityUser = this.securityUserMapper.toSecurityUser(registrationRequest, encodedPassword);
        return this.executeDbOperation(() -> this.securityUserRepository.save(securityUser), this.registrationResponseMapper::toRegistrationResponse);
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest, HttpServletResponse response) {
        SecurityUser securityUser = this.securityUserRepository.findSecurityUserByUsername(loginRequest.username()).orElseThrow(WrongUsernameOrPasswordException::new);
        if (!passwordEncoder.matches(loginRequest.password(), securityUser.getPassword())) {
            throw new WrongUsernameOrPasswordException();
        }

        log.info("Login User: {}", loginRequest.username());
        String userId = securityUser.getUserId().toString();
        Session session = this.tokenUtil.createAndStoreSession(userId);

        this.tokenUtil.setRefreshTokenCookie(session.refreshToken(), response);
        return this.tokenUtil.generateSessionResponse(session.sessionToken());
    }

    public ResponseEntity<?> tokenRefresh(HttpServletRequest request, HttpServletResponse response) {
        log.info("Token Refresh Request: {}", request.getCookies());
        return null;
    }
}
