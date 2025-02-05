package com.mrbean.openai.domain;

import lombok.Data;

import java.util.List;

@Data
public class OpenAIRequestDTO {
    private String model;
    private List<Message> messages;

    public OpenAIRequestDTO(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

    public static class Message {
        private String role;
        private String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String getRole() { return role; }
        public String getContent() { return content; }
    }

    public String getModel() { return model; }
    public List<Message> getMessages() { return messages; }
}