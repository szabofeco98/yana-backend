package com.fec.yana.authentication.dto.response;


public record LoginResponse(String sessionToken, String refreshToken) {}