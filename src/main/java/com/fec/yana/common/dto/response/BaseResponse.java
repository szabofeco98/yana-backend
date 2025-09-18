package com.fec.yana.common.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseResponse<T> {
    private HttpStatus status;
    private String message;
    private T data;

    public BaseResponse(T data) {
        this.data = data;
    }
}
