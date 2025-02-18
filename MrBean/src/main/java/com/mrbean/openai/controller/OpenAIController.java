package com.mrbean.openai.controller;

import com.mrbean.openai.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OpenAIController {

    @Autowired
    private final OpenAIService openAIService;

    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/openai")
    public String chat(@RequestBody String userInput) {
        String response = openAIService.chatWithOpenAi(userInput);
        return response;
    }
}