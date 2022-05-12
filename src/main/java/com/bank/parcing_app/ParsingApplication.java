package com.bank.parcing_app;

import com.bank.parcing_app.service.ExecutionService;
import com.bank.parcing_app.service.ServiceMain;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.stream.Stream;

@SpringBootApplication
@AllArgsConstructor
@EnableScheduling
class ParsingApplication implements CommandLineRunner {
    private final ServiceMain serviceMain;
    private final ExecutionService  executionService;

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ParsingApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Stream.of(args)
                .forEach(serviceMain::doWork);
        executionService.shutdown();
    }
}
