package com.fec.yana.authentication.dto.response;

import java.util.UUID;

public record RegistrationResponse(
        String username,
        UUID userId) { }
