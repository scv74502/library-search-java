package com.library.service;

import com.library.controller.response.PageResult;
import com.library.controller.response.SearchResponse;
import com.library.entity.DailyStat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class BookApplicationService {
    private final BookQueryService bookQueryService;
    private final DailyStatCommandService dailyStatCommandService;

    public PageResult<SearchResponse> search(String query, int page, int size){
        // 외부 api호출 -> 통계데이터 저장 -> api호출값 응답
        // 외부 api 응답받아 db에 통계 저장 완료 전까지는 응답 받지 못함
        PageResult<SearchResponse> response = bookQueryService.search(query, page, size);
        DailyStat dailyStat = new DailyStat(query, LocalDateTime.now());
        dailyStatCommandService.save(dailyStat);
        return response;
    }
}
