package com.library.controller.response;

import com.library.ErrorType;

public record ErrorResponse(String errorMessage, ErrorType errorType){
}
