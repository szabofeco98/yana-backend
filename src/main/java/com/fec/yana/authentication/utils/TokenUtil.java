package com.fec.yana.authentication.utils;

import com.fec.yana.authentication.constants.AuthConfig;
import com.fec.yana.authentication.constants.UrlPath;
import com.fec.yana.authentication.dto.util.Session;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenUtil {
    private final StringRedisTemplate redisTemplate;

    public Session createAndStoreSession(String userId) {
        String sessionToken = UUID.randomUUID().toString();
        String refreshToken = UUID.randomUUID().toString();

        redisTemplate.opsForValue().set(String.format("%s:%s", AuthConfig.sessionToken, sessionToken), userId, Duration.ofMinutes(AuthConfig.sessionTokenExpiresInMinutes));
        redisTemplate.opsForValue().set(String.format("%s:%s", AuthConfig.refreshToken, refreshToken), userId, Duration.ofDays(AuthConfig.refreshTokenExpiresInDays));

        return new Session(sessionToken, refreshToken);
    }

    public void setRefreshTokenCookie(String refreshToken, HttpServletResponse response) {
        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path(UrlPath.TOKEN_REFRESH)
                .maxAge(Duration.ofDays(AuthConfig.refreshTokenExpiresInDays))
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    }

    public ResponseEntity<?> generateSessionResponse(String sessionToken) {
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + sessionToken)
                .build();
    }
}
