package com.bank.parcing_app.service.serviceimpl;

import com.bank.parcing_app.domain.FileType;
import com.bank.parcing_app.domain.Order;
import com.bank.parcing_app.domain.OrderData;
import com.bank.parcing_app.mapper.OrderDataMapperToOrder;
import com.bank.parcing_app.mapper.OrderMapperToJson;
import com.bank.parcing_app.service.ExecutionService;
import com.bank.parcing_app.service.ParsingService;
import com.bank.parcing_app.util.SimpleLogger;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class CsvParsingService implements ParsingService {
    private final ExecutionService executionService;
    private final SimpleLogger logger;

    @Override
    public void parse(File file) {
        try {
            final List<String> csvFileLines = FileUtils.readLines(file, "UTF-8");
            AtomicInteger counter = new AtomicInteger(1);
            csvFileLines.forEach(e -> {
                final String[] split = e.split(",");
                final OrderData orderData = new OrderData(split[0], split[1], split[2], split[3],
                        counter.getAndIncrement());

                executionService.submit(() -> {
                    try {
                        final Order order = OrderDataMapperToOrder.mapToPojo(orderData, file.getName());
                        final String output = OrderMapperToJson.mapToJson(order);
                        logger.log(output);
                    } catch (IOException ignored) {
                        //ignore
                    }
                });
            });
        } catch (Exception ignored) {
            //ignore
        }
    }

    @Override
    public FileType getType() {
        return FileType.CSV;
    }
}
