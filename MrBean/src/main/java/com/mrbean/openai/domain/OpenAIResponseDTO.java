package com.mrbean.openai.domain;

import lombok.Data;

import java.util.List;

@Data
public class OpenAIResponseDTO {
    private List<Choice> choices;

    public List<Choice> getChoices() { return choices; }

    public static class Choice {
        private Message message;
        public Message getMessage() { return message; }
    }

    public static class Message {
        private String role;
        private String content;
        public String getRole() { return role; }
        public String getContent() { return content; }
    }
}
