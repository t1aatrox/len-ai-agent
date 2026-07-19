package com.lenyan.lenaiagent.demo.invoke;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringAiTest.TestConfig.class)
class SpringAiTest {

    @SpringBootConfiguration
    @EnableAutoConfiguration(exclude = {
            org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
            org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration.class,
            org.springframework.ai.autoconfigure.vectorstore.pgvector.PgVectorStoreAutoConfiguration.class
    })
    static class TestConfig {
    }

    @Resource
    private ChatModel ollamaChatModel;

    @Resource
    private ChatModel dashscopeChatModel;

    @Resource
    private ChatModel openAiChatModel;

    @Test
    void testAllModels() {
        runTestWithClient("Ollama", ollamaChatModel);

        runTestWithClient("通义千问", dashscopeChatModel);

        runTestWithClient("OpenAI", openAiChatModel);
    }

    private void runTestWithClient(String label, ChatModel chatModel) {
        try {
            System.out.println("\n[正在测试] -> " + label);

            ChatClient chatClient = ChatClient.builder(chatModel).build();

//            String text = chatModel.call(new Prompt("hi")).getResult().getOutput().getText();

            String reply = chatClient.prompt("你好，收到请回复'pong'，并介绍你自己")
                    .call()
                    .content();

            System.out.println("[" + label + " 成功]: " + reply);
        } catch (Exception e) {
            System.err.println("[" + label + " 失败]: " + e.getMessage());
        }
    }
}
