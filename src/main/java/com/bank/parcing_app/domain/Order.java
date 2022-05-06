package com.bank.parcing_app.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class Order {
        private long id;
        private Long orderId;
        private BigDecimal amount;
        private String currency;
        private String comment;
        private String filename;
        private int line;
        private String result;
}
