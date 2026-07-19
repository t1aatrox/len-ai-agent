package com.lenyan.lenaiagent.demo.invoke;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import static dev.langchain4j.model.chat.request.ResponseFormat.JSON;

public class LangChain4jDemo {

    public static void main(String[] args) {
        ChatModel model = QwenChatModel.builder()
                .apiKey(System.getenv("DASHSCOPE_API_KEY"))
                .modelName("qwen3.6-max-preview")
                .build();
        System.out.println("LangChainAi调用：" + model.chat("你好，收到请回复yes，并介绍你自己"));
    }
}

class OllamaChatLocalModelTest {
    static String MODEL_NAME = "qwen2.5:7b";
    static String BASE_URL = "http://localhost:11434";

    public static void main(String[] args) {
        ChatModel model = OllamaChatModel.builder()
                .baseUrl(BASE_URL)
                .modelName(MODEL_NAME)
                .build();
        String answer = model.chat("List top 10 cites in China");
        System.out.println(answer);

        model = OllamaChatModel.builder()
                .baseUrl(BASE_URL)
                .modelName(MODEL_NAME)
                .responseFormat(JSON)
                .build();

        String json = model.chat("List top 10 cites in US");
        System.out.println(json);
    }
}
