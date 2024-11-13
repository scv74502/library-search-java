package com.library.controller.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchRequest {
    // 50 자
    @Size(max = 50, message = "query 입력은 최대 50자를 최대할 수 없습니다")
    @NotBlank(message = "query 입력은 비어있을 수 없습니다")
    private String query;
    // 1 ~ 50
    @NotNull(message = "page 번호 입력은 비어있을 수 없습니다")
    @Min(value = 1, message = "page number must bigger than 1")
    @Max(value = 10000, message = "page number must smaller than 10000")
    private int page;
    // 1 ~ 50
    @NotNull(message = "page 크기 입력은 비어있을 수 없습니다")
    @Min(value = 1, message = "page size number must bigger than 1")
    @Max(value = 50, message = "page size number must smaller than 50")
    private int size;
}
