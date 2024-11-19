package com.library.service;

import com.library.controller.response.StatResponse;
import com.library.repository.DailyStatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;


@RequiredArgsConstructor
@Service
public class DailyStatQueryService {
    private final DailyStatRepository dailyStatRepository;

    public StatResponse findQueryCount(String query, LocalDate date) {
        long count = dailyStatRepository.countByQueryAndEventDateTimeBetween(
                query,
                date.atStartOfDay(),
                date.atTime(LocalTime.MAX)
        );

        return new StatResponse(query, count);
    }
}