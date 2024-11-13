package com.example.foodorderplatform.controller;

import com.example.foodorderplatform.dto.FoodTagRequestDto;
import com.example.foodorderplatform.service.FoodTagService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store/{storeId}/foodtag")
@RequiredArgsConstructor
public class FoodTagController {

    private final FoodTagService foodTagService;

    @PostMapping
    public ResponseEntity<String> createFoodTag(@PathVariable UUID storeId, @RequestBody FoodTagRequestDto requestDto){
        return foodTagService.createFoodTag(storeId, requestDto);
    }
}
