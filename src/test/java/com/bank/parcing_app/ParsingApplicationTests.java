package com.bank.parcing_app;

import com.bank.parcing_app.util.SimpleLogger;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.Duration;

@SpringBootTest(args = {"orderCsv.csv"})
class ParsingApplicationTests {
    @SpyBean
    SimpleLogger logger;

    @Test
    void appTest() {
        String first = "{\"id\":0,\"orderId\":1,\"amount\":null,\"currency\":\"USD\",\"comment\":\"оплата заказа\",\"filename\":\"orderCsv.csv\",\"line\":1,\"result\":\"Invalid format for field(s): [amount]\"}";
        String second = "{\"id\":1,\"orderId\":2,\"amount\":123.0,\"currency\":\"EUR\",\"comment\":\"оплата заказа\",\"filename\":\"orderCsv.csv\",\"line\":2,\"result\":\"OK\"}";

        //test
        Awaitility.await()
                .atMost(Duration.ofSeconds(5))
                .untilAsserted(() -> {
                    Mockito.verify(logger).log(second);
                    Mockito.verify(logger).log(first);
                });
    }
}
