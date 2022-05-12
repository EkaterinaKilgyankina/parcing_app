package com.bank.parcing_app.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ExecutionService {
    private final ExecutorService pool = Executors.newFixedThreadPool(4);

    public void submit(Runnable runnable) {
        pool.submit(runnable);
    }

    public void shutdown() {
        pool.shutdown();
    }

    @Scheduled(fixedRate = 500L)
    public void isShutDown() {
        if (pool.isShutdown()) {
            System.exit(0);
        }
    }
}
