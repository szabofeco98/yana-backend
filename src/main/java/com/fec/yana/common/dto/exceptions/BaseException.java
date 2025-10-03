package com.fec.yana.common.dto.exceptions;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
    private HttpStatus httpStatus;
    private String errorCode;

    public BaseException(HttpStatus status, String errorCode, String message) {
        super(message);
        this.httpStatus = status;
        this.errorCode = errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
