package com.fec.yana.common.controller;

import com.fec.yana.common.constants.BaseErrorCode;
import com.fec.yana.common.dto.exceptions.BaseException;
import com.fec.yana.common.dto.response.BaseErrorResponse;
import com.fec.yana.common.mapper.BaseErrorResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {
    private final BaseErrorResponseMapper baseErrorResponseMapper;

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseErrorResponse> handleAppException(BaseException e) {
        BaseErrorResponse response = this.baseErrorResponseMapper.toBaseErrorResponse(e);
        return ResponseEntity.status(e.getHttpStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseErrorResponse> handleGeneric(Exception e) {
        // fallback for unexpected errors
        BaseErrorResponse response = new BaseErrorResponse();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setMessage("Unexpected error occurred");
        response.setErrorCode(BaseErrorCode.UNKNOWN_EXCEPTION);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .findFirst()
                .orElse("Validation error");

        BaseErrorResponse response = new BaseErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setMessage(errorMessage);
        response.setErrorCode(BaseErrorCode.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
