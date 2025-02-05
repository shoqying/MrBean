package com.mrbean.openai.service;

import com.mrbean.openai.domain.OpenAIRequestDTO;
import com.mrbean.openai.domain.OpenAIResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Collections;

@Service
public class OpenAIService {

    @Value("${openai.api-key}")
    private String apiKey;

    @Value("${openai.api-url}")
    private String apiUrl;

    @Value("${openai.model}")
    private String model;

    private final RestTemplate restTemplate;

    public OpenAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String chatWithOpenAi(String userInput) {
        OpenAIRequestDTO.Message message = new OpenAIRequestDTO.Message("user", userInput);
        OpenAIRequestDTO request = new OpenAIRequestDTO(model, Collections.singletonList(message));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<OpenAIRequestDTO> entity = new HttpEntity<>(request, headers);
        ResponseEntity<OpenAIResponseDTO> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, OpenAIResponseDTO.class);

        if (response.getBody() != null && response.getBody().getChoices() != null && !response.getBody().getChoices().isEmpty()) {
            return response.getBody().getChoices().get(0).getMessage().getContent();
        } else {
            return "No response from OpenAI";
        }
    }
}


