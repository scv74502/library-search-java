package com.library.service.event;

import com.library.entity.DailyStat;
import com.library.service.DailyStatCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Slf4j
@Component
@Repository
public class SearchEventHandler {
    private final DailyStatCommandService dailyStatCommandService;

    public SearchEventHandler(DailyStatCommandService dailyStatCommandService) {
        this.dailyStatCommandService = dailyStatCommandService;
    }

    @Async
    @EventListener
    public void handleEvent(SearchEvent event) throws InterruptedException {
//        Thread.sleep(5000L);
        log.info("[SearchEventHandler] handleEvent: {}", event);
        DailyStat dailyStat = new DailyStat(event.query(), LocalDateTime.now());
        dailyStatCommandService.save(dailyStat);
    }
}
