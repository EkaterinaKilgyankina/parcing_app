package com.bank.parcing_app.service;

import com.bank.parcing_app.domain.FileType;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @SneakyThrows
    public static void main(String[] args) {
//        final File folder = new File("/Users/ekaterinakilgankina/Desktop/Explore-logs-2023-04-17 14_18_31.txt");
//        final File folder = new File("/Users/ekaterinakilgankina/Desktop/Explore-logs-2023-04-17 16_39_21.txt");
//        final File folder = new File("/Users/ekaterinakilgankina/Desktop/Explore-logs-2023-04-17 17_01_19.txt");
        final File folder = new File("/Users/ekaterinakilgankina/Desktop/Explore-logs-2023-04-18 10_40_45.txt");
        final List<String> csvFileLines = FileUtils.readLines(folder, "UTF-8");
        Map<String, List<String>> elements = Stream.of("makePdf ms","page load ms","screenshot ms","ImageIO.write ms","pdf write ms", "run time")
                .collect(Collectors.toMap(
                        Function.identity(),
                        key -> csvFileLines.stream()
                                .filter(e -> e.contains(key))
                                .collect(Collectors.toList())
                ));
        Map<String, Double> service = Stream.of("makePdf ms","page load ms","screenshot ms","ImageIO.write ms","pdf write ms")
                .collect(Collectors.toMap(
                        Function.identity(),
                        key -> csvFileLines.stream()
                                .filter(e -> e.contains(key))
                                .mapToLong(e -> Long.parseLong(e.split("ms - ")[1]))
                                .average()
                                .orElse(0)
                ));

        Map<String, Double> util = Stream.of("run time")
                .collect(Collectors.toMap(
                        Function.identity(),
                        key -> csvFileLines.stream()
                                .filter(e -> e.contains(key))
                                .mapToLong(e -> Long.parseLong(e.split("run time ")[1]))
                                .average()
                                .orElse(0)
                ));

        System.out.println(util);
    }
}
