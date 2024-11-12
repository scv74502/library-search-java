package com.library;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException{
    private final String errorMsg;
    private final ErrorType errorType;
    private final HttpStatus httpStatus;

    public ApiException(String errorMsg, ErrorType errorType, HttpStatus httpStatus) {
        this.errorMsg = errorMsg;
        this.errorType = errorType;
        this.httpStatus = httpStatus;
    }
}
