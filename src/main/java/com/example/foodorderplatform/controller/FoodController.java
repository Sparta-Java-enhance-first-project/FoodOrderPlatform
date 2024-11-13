package com.example.foodorderplatform.controller;

import com.example.foodorderplatform.dto.FoodRequestDto;
import com.example.foodorderplatform.dto.FoodResponseDto;
import com.example.foodorderplatform.service.FoodService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store/{storeId}/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    //음식 관리 페이지 요청
    // 일단 보류

    //음식 생성
    @PostMapping
    public ResponseEntity<String> createFood(@PathVariable UUID storeId,
                                             @RequestBody FoodRequestDto requestDto){
        return foodService.createFood(storeId, requestDto);
    }

    //음식 목록 조회
    @GetMapping
    public ResponseEntity<List<FoodResponseDto>> getFoodList(@PathVariable UUID storeId){
        return foodService.getFoodList(storeId);
    }
    //음식 단일 조회
    @GetMapping("/{foodId}")
    public ResponseEntity<FoodResponseDto> getFood(@PathVariable UUID storeId, @PathVariable UUID foodId){
        return foodService.getFood(storeId, foodId);
    }
    //음식 수정
    @PatchMapping("/{foodId}")
    public ResponseEntity<String> updateFood(@PathVariable UUID storeId, @PathVariable UUID foodId,
                                                      @RequestBody FoodRequestDto requestDto){
        return foodService.updateFood(storeId, foodId, requestDto);
    }

    //음식 삭제
    // 삭제는 soft delete를 해야 해서 추후 구현
}
