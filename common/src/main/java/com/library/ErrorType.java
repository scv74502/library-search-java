package com.library;

import lombok.Getter;

@Getter
public enum ErrorType {
    EXTERNAL_API_ERROR("외부 API 호출 에러 입니다."),
    UNKNOWN("알 수 없는 에러 입니다."),
    INVALID_PARAMETER("잘못된 요청값 입니다."),
    NO_RESOURCE("리소스를 찾을 수 없습니다.");

    ErrorType(String description) {
        this.description = description;
    }

    private final String description;
}
