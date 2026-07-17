package com.lenyan.lenaiagent.demo.invoke;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

/**
 * Spring AI 框架调用 AI 大模型（Ollama）
 */
 @SpringBootTest
class OllamaAiInvokeTest {
    @Resource
    private ChatModel ollamaChatModel;

    @Test
    void testOllamaChat() {
        System.out.println("ollama调用: " +
                ollamaChatModel.call(new Prompt("你好，我是lenyan"))
                        .getResult()
                        .getOutput()
                        .getText());
    }
}
