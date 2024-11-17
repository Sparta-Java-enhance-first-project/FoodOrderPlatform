package com.example.foodorderplatform.controller;

import com.example.foodorderplatform.dto.FoodTagRequestDto;
import com.example.foodorderplatform.dto.FoodTagResponseDto;
import com.example.foodorderplatform.security.UserDetailsImpl;
import com.example.foodorderplatform.service.FoodTagService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    /**
     * 음식 태그 생성
     * @param storeId 가게 아이디
     * @param requestDto 가게에서 생성하려는 음식 태그 이름
     * @return 음식 태그 생성 메세지
     */
    @PostMapping
    public ResponseEntity<String> createFoodTag(@PathVariable UUID storeId, @RequestBody FoodTagRequestDto requestDto){
        return foodTagService.createFoodTag(storeId, requestDto);
    }

    /**
     * 음식 태그 목록 조회
     * @param userDetails 사용자 정보
     * @param storeId 가게 아이디
     * @return 음식 태그 목록
     */
    @GetMapping
    public ResponseEntity<List<FoodTagResponseDto>> getFoodTagList(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                   @PathVariable UUID storeId){
        return foodTagService.getFoodTagList(userDetails.getUser(), storeId);
    }

    /**
     *
     * @param userDetails 사용자 정보
     * @param storeId 가게 아이디
     * @param foodTagId 수정할 음식 태그 아이디
     * @param requestDto 변경될 음식 태그 이름
     * @return 요청 성공 메세지
     */
    @PatchMapping("{foodTagId}")
    public ResponseEntity<String> updateFoodTag(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                @PathVariable UUID storeId,
                                                @PathVariable UUID foodTagId,
                                                @RequestBody FoodTagRequestDto requestDto){
        return foodTagService.updateFoodTag(userDetails.getUser(), storeId, foodTagId, requestDto);
    }
}
