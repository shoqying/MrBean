package com.mrbean.openai.service;

import com.mrbean.openai.domain.OpenAIRequestDTO;
import com.mrbean.openai.domain.OpenAIResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
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
        this.restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    public String chatWithOpenAi(String userInput) {

        OpenAIRequestDTO.Message message = new OpenAIRequestDTO.Message("user", userInput);
        OpenAIRequestDTO request = new OpenAIRequestDTO(model, Collections.singletonList(message));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));

        HttpEntity<OpenAIRequestDTO> entity = new HttpEntity<>(request, headers);
        ResponseEntity<OpenAIResponseDTO> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, OpenAIResponseDTO.class);

        if (response.getBody() != null && response.getBody().getChoices() != null && !response.getBody().getChoices().isEmpty()) {
            String result = response.getBody().getChoices().get(0).getMessage().getContent();
            return result;
        } else {
            return "No response from OpenAI";
        }
    }
}