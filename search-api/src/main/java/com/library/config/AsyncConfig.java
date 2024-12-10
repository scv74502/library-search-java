package com.library.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Executor;


// 테스트 용도
@Slf4j
@Configuration
public class AsyncConfig implements AsyncConfigurer {
    @Bean("lsExecutor")
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 실행 환경의 cpu 코어 수
        int cpuCoreCount = Runtime.getRuntime().availableProcessors();
        // 기본적으로 유지되는 스레드 풀의 최소 스레드 수
        executor.setCorePoolSize(cpuCoreCount);
        // 코어 풀 사이즈 이상으로 작업이 들어왔을 때 최대 스레드 수
        executor.setMaxPoolSize(cpuCoreCount * 2);
        // 작업 큐의 용량 설정, 추가 작업들은 거부되거나 다른 설정대로 처리됨
        executor.setQueueCapacity(10);

//        // 기본적으로 유지되는 스레드 풀의 최소 스레드 수
//        executor.setCorePoolSize(2);
//        // 코어 풀 사이즈 이상으로 작업이 들어왔을 때 최대 스레드 수
//        executor.setMaxPoolSize(4);
//        // 작업 큐의 용량 설정, 추가 작업들은 거부되거나 다른 설정대로 처리됨
//        executor.setQueueCapacity(2);

        // 최대 스레드 수 초과하는 여분 스레드가 대기작업 없이 유지되는 시간 설정
        executor.setKeepAliveSeconds(60);
        // 애플리케이션 종료시 스레드풀에 작업이 남았으면 작업을 수행하는 설정
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        // 스레드 풀의 접두사
        executor.setThreadNamePrefix("LS-");
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {   // async 어노테이션에서 발생한 예외 처리
        return new CustomAsyncExceptionHandler();
//        return AsyncConfigurer.super.getAsyncUncaughtExceptionHandler();
    }

    private static class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable ex, Method method, Object... params) {
            log.error("Failed to execute async method:{}", ex.getMessage());
            Arrays.asList(params).forEach(param -> log.info("parameter value:{}", param));
        }
    }

    // main thread 터지지 않게 방지하는 커스텀
//    private static class CustomThreadPoolExecutor implements RejectedExecutionHandler {
//        @Override
//        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
//
//        }
//    }

}
