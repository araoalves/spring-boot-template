package com.template.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;

import com.template.service.resttemplate.IConsumer;

import lombok.Data;
import lombok.ToString;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ConsumerTemplateTest {

    @Autowired
    private IConsumer consumer;

    @Test
    public void get() throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("header1","value1");
        headers.put("header2", "value2");

        TestJson o = (TestJson) consumer.get("http://echo.jsontest.com/key/value/one/two", TestJson.class, MediaType.APPLICATION_JSON, headers);
        Assertions.assertNotNull(o);
    }

}

@Data
@ToString
class TestJson{
    private String one;
    private String key;
}

