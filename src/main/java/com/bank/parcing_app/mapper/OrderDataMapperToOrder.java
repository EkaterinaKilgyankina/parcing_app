package com.bank.parcing_app.mapper;

import com.bank.parcing_app.domain.Order;
import com.bank.parcing_app.domain.OrderData;
import com.bank.parcing_app.util.IdGeneratorUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

@Component
public class OrderDataMapperToOrder {

    public static Order mapToPojo(OrderData data, String fileName) {
        Order result = new Order();
        List<String> errorMessages = new ArrayList<>();
        result.setId(IdGeneratorUtil.counter.getAndIncrement());
        result.setFilename(fileName);
        result.setLine(data.getLine());

        try {
            result.setOrderId(Long.valueOf(String.valueOf(data.getOrderId())));
        } catch (IllegalArgumentException ex) {
            errorMessages.add("orderId");
        }
        try {
            result.setAmount(BigDecimal.valueOf(parseDouble(String.valueOf(data.getAmount()))));
        } catch (IllegalArgumentException ex) {
            errorMessages.add("amount");
        }
        try {
            result.setComment(String.valueOf(data.getComment()));
        } catch (IllegalArgumentException ex) {
            errorMessages.add("comment");
        }
        try {
            result.setCurrency(String.valueOf(data.getCurrency()));
        } catch (IllegalArgumentException ex) {
            errorMessages.add("currency");
        }
        result.setResult(errorMessages.isEmpty() ? "OK" : "Invalid format for field(s): " + errorMessages.toString());

        return result;
    }
}
