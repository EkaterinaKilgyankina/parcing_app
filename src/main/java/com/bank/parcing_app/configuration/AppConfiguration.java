package com.bank.parcing_app.configuration;

import com.bank.parcing_app.domain.FileType;
import com.bank.parcing_app.service.ParsingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class AppConfiguration {

    @Bean
    Map<FileType, ParsingService> parsingServices(List<ParsingService> services) {
        return services.stream()
                .collect(Collectors.toMap(ParsingService::getType, Function.identity()));
    }
}
