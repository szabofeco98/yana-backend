package com.fec.yana.authentication.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    String username,
    @NotBlank(message = "Password is required")
    @Size(min = 3, max = 200, message = "Password must be between 3 and 200 characters")
    String password)
{}
