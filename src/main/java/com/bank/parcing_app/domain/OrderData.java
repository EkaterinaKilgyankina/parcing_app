package com.bank.parcing_app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderData {
    private Object orderId;
    private Object amount;
    private Object currency;
    private Object comment;
    private int line;
}
