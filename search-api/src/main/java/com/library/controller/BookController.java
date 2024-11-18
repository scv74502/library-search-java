package com.library.controller;

import com.library.controller.request.SearchRequest;
import com.library.controller.response.PageResult;
import com.library.controller.response.SearchResponse;
import com.library.service.BookApplicationService;
import com.library.service.BookQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookApplicationService bookApplicationService;

    @GetMapping
    public PageResult<SearchResponse> search(@Valid SearchRequest searchRequest) {

        return bookApplicationService.search(searchRequest.getQuery(), searchRequest.getPage(), searchRequest.getSize());
    }
}
