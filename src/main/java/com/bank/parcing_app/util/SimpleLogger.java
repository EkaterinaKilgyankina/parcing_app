package com.bank.parcing_app.util;


import org.springframework.stereotype.Component;

@Component
public class SimpleLogger {
    public void log (String jsonString) {
        System.out.println(jsonString);
    }
}
