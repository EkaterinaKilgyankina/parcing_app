package com.bank.parcing_app.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ExecutionService {
    private final ExecutorService pool = Executors.newFixedThreadPool(4);

    public void submit(Runnable runnable) {
        pool.submit(runnable);
    }
}
