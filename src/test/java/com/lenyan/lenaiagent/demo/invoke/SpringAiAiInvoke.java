package com.lenyan.lenaiagent.demo.invoke;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringAIInvokeTest {

    @Resource
    private ChatModel dashscopeChatModel;

    // @Resource
    // private ChatClient chatClient;

    @Test
    void testSpringAIChat() {
        System.out.println("springai调用:" + dashscopeChatModel.call(new Prompt("你好，我是lenyan")).getResult().getOutput().getText());

        // chatClient.prompt("你好，我是lenyan").call();
    }
}
