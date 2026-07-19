package com.lenyan.lenaiagent.demo.invoke;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;

/**
 * Spring AI 框架调用 AI 大模型（Ollama）
 */
public class OllamaDemo {

    public static void main(String[] args) {
        // 本地 Ollama 默认服务地址
        String baseUrl = "http://localhost:11434";
        System.out.println("🔄 正在连接本地 Ollama 服务 (" + baseUrl + ")...");

        try {
            OllamaApi ollamaApi = new OllamaApi(baseUrl);

            // 配置模型及参数（请确保你本地执行过 `ollama pull qwen2.5:7b` 或其他模型）
            OllamaOptions options = OllamaOptions.builder()
                    .model("qwen2.5:7b")
                    .temperature(0.7)
                    .build();

            OllamaChatModel chatModel = OllamaChatModel.builder()
                    .ollamaApi(ollamaApi)
                    .defaultOptions(options)
                    .build();

            System.out.println("💬 正在发送提示词，等待本地模型响应...");
            String question = "你好，请用一句话介绍你自己。";

            ChatResponse response = chatModel.call(new Prompt(question));

            if (response != null && response.getResult() != null && response.getResult().getOutput() != null) {
                String content = response.getResult().getOutput().getText();
                System.out.println("\n✅ 本地模型响应结果：");
                System.out.println(content);
            } else {
                System.out.println("❌ 模型未返回任何有效内容。");
            }
        } catch (Exception e) {
            System.err.println("\n❌ 连接本地 Ollama 失败，请检查服务是否启动。");
            e.printStackTrace();
        }
    }
}
