package com.example.SmartRestaurant.config.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class AsyncConfig {
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5);     // số thread chạy song song
        executor.setMaxPoolSize(10);     // tối đa
        executor.setQueueCapacity(100);  // hàng đợi

        executor.setThreadNamePrefix("Async-");

        executor.initialize();
        return executor;
    }
}
