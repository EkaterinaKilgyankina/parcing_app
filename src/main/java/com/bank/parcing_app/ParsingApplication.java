package com.bank.parcing_app;

import com.bank.parcing_app.service.ServiceMain;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Stream;

@SpringBootApplication
@AllArgsConstructor
class ParsingApplication implements CommandLineRunner {
    private final ServiceMain serviceMain;

    public static void main(String[] args) {
        SpringApplication.run(ParsingApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Stream.of(args)
                .forEach(serviceMain::doWork);
    }

}
