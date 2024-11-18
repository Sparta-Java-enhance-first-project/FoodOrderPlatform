package com.example.foodorderplatform.controller;

import com.example.foodorderplatform.dto.AIScriptRequestDto;
import com.example.foodorderplatform.security.UserDetailsImpl;
import com.example.foodorderplatform.service.AIScriptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIScriptController {

    private final AIScriptService aiScriptService;

    @PostMapping
    public ResponseEntity<String> createAIScript(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                              @RequestBody AIScriptRequestDto requestDto){
        return aiScriptService.createAIScript(requestDto.getScriptQuestion(), userDetails.getUser());
    }
}
