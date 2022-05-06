package com.bank.parcing_app.service;

import com.bank.parcing_app.domain.FileType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.Map;

@Component
@AllArgsConstructor
public class ServiceMain {
    private final Map<FileType, ParsingService> parsingServices;

    public void doWork(String fileName) {
        FileType fileType = FileType.resolveByFileName(fileName);

        ParsingService service = parsingServices.get(fileType);

        if (service == null) {
            throw new UnsupportedOperationException("file format not supported");
        }

        service.parse(Paths.get(fileName).toAbsolutePath().toFile());
    }
}
