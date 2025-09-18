package com.fec.yana.common.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseErrorResponse {
    private HttpStatus status;
    private String message;
    private String errorCode;
}
