package com.fec.yana.common.service;

import com.fec.yana.common.dto.exceptions.DuplicateEntryException;
import com.fec.yana.common.dto.exceptions.ServiceUnavailableException;
import com.fec.yana.common.dto.response.BaseResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class BaseService {
    public <T> BaseResponse<T> createResponse(T data, HttpStatus status, String message) {
        BaseResponse<T> response = new BaseResponse<T>(data);
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }

    public <T> BaseResponse<T> createResponse(T data, String message) {
        return this.createResponse(data, HttpStatus.OK, message);
    }

    public <T> BaseResponse<T> createResponse(T data,  HttpStatus status) {
       return this.createResponse(data, status, "");
    }

    public <T> BaseResponse<T> createResponse(T data) {
        return this.createResponse(data, "");
    }

    public <ENTITY, RESPONSE> BaseResponse<RESPONSE> executeDbOperation(Supplier<ENTITY> operation, Function<ENTITY, RESPONSE> mapper) {
        try {
            ENTITY entity = operation.get();
            return this.createResponse(mapper.apply(entity));
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntryException();
        } catch (Exception e) {
            throw new ServiceUnavailableException();
        }
    }
}
