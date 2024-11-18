package com.example.foodorderplatform.service;

import com.example.foodorderplatform.dto.AIScriptResponseDto;
import com.example.foodorderplatform.entity.AiScript;
import com.example.foodorderplatform.entity.User;
import com.example.foodorderplatform.repository.AIScriptRepository;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Transactional
@Service
@RequiredArgsConstructor
public class AIScriptService {

    private final AIScriptRepository aiScriptRepository;
    @Value(value = "${ai.api.key}")
    private String AI_KEY;

    public ResponseEntity<String> createAIScript(String scriptQuestion, User user) {
        // Create the inner "text" object
        JSONObject textObject = new JSONObject();
        textObject.put("text", scriptQuestion);

        // Create the "parts" array and add the "text" object
        JSONArray partsArray = new JSONArray();
        partsArray.put(textObject);

        // Create the "contents" object with "parts" array
        JSONObject contentObject = new JSONObject();
        contentObject.put("parts", partsArray);

        // Create the "contents" array and add the "content" object
        JSONArray contentsArray = new JSONArray();
        contentsArray.put(contentObject);

        // Create the final JSON object
        JSONObject requestBody = new JSONObject();
        requestBody.put("contents", contentsArray);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
        URI uri = UriComponentsBuilder
                .fromUriString("https://generativelanguage.googleapis.com")
                .path("/v1beta/models/gemini-1.5-flash-latest:generateContent")
                .queryParam("key", AI_KEY)
                .encode()
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AIScriptResponseDto> response = restTemplate.postForEntity(
                uri, request, AIScriptResponseDto.class
        );

        AIScriptResponseDto responseBody = response.getBody();
        if (responseBody == null || responseBody.getCandidates().isEmpty()) {
            throw new IllegalStateException("Invalid response from AI service");
        }

        String scriptAnswer = responseBody.getCandidates()
                .get(0) // 첫 번째 후보
                .getContent()
                .getParts()
                .get(0) // 첫 번째 파트
                .getText();
        aiScriptRepository.save(new AiScript(scriptQuestion, scriptAnswer, user));
        return new ResponseEntity<>(scriptAnswer, HttpStatus.OK);
    }
}
