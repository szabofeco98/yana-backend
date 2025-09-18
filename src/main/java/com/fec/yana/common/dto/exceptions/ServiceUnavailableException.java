package com.fec.yana.common.dto.exceptions;

import com.fec.yana.common.constants.BaseErrorCode;
import org.springframework.http.HttpStatus;

public class ServiceUnavailableException extends BaseException {
    public ServiceUnavailableException() {
        super(HttpStatus.SERVICE_UNAVAILABLE, BaseErrorCode.SERVICE_UNAVAILABLE, "Service Unavailable");
    }
}
