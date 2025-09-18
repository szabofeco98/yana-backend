package com.fec.yana.common.dto.exceptions;

import com.fec.yana.common.constants.BaseErrorCode;
import org.springframework.http.HttpStatus;

public class DuplicateEntryException extends BaseException {
    public DuplicateEntryException() {
        super(HttpStatus.BAD_REQUEST, BaseErrorCode.DUPLICATED_ENTRY, "Duplicated entry");
    }
}
