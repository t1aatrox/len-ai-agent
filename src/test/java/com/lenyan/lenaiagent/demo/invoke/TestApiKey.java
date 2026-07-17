package com.lenyan.lenaiagent.demo.invoke;

import org.springframework.beans.factory.annotation.Value;

public class TestApiKey {

    @Value("${qwen.api-key}")
    public static String API_KEY;

}
