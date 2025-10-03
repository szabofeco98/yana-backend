package com.fec.yana.authentication.dto.exceptions;

import com.fec.yana.authentication.constants.AuthenticationErrorCode;
import com.fec.yana.common.dto.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class WrongUsernameOrPasswordException extends BaseException {
    public WrongUsernameOrPasswordException() {
        super(HttpStatus.UNAUTHORIZED, AuthenticationErrorCode.WRONG_USERNAME_OR_PASSWORD, "Wrong username or password");
    }
}
