package com.fec.yana.common.dto.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseException extends RuntimeException {
    private HttpStatus httpStatus;
    private String errorCode;

    public BaseException(HttpStatus status, String errorCode, String message) {
        super(message);
        this.httpStatus = status;
        this.errorCode = errorCode;
    }
}
