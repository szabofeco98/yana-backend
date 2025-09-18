package com.fec.yana.authentication.dto.exceptions;

import com.fec.yana.authentication.constants.AuthenticationErrorCode;
import com.fec.yana.common.dto.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class UsernameAlreadyExistsException extends BaseException {
    public UsernameAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, AuthenticationErrorCode.USERNAME_ALREADY_EXISTS, "Username already exists");
    }
}
