package com.example.foodorderplatform.controller;

import com.example.foodorderplatform.dto.FoodRequestDto;
import com.example.foodorderplatform.dto.FoodResponseDto;
import com.example.foodorderplatform.security.UserDetailsImpl;
import com.example.foodorderplatform.service.FoodService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store/{storeId}/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    //음식 관리 페이지 요청

    /**
     * 음식 목록 조회
     * @param storeId 가게 아이디
     * @return 가게 음식 목록 정보
     */
    @GetMapping("/setting")
    public ResponseEntity<List<FoodResponseDto>> getFoodSettingInfo(@PathVariable UUID storeId,
                                                                    @RequestParam(defaultValue = "추천") String foodTagName) {
        return foodService.getFoodList(storeId, foodTagName);
    }

    /**
     * 음식 생성
     *
     * @param userDetails 사용자 정보
     * @param storeId     가게 아이디
     * @param requestDto  생성할 음식 정보 양식
     * @return 요청 성공 메세지
     */
    @PostMapping
    public ResponseEntity<String> createFood(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                             @PathVariable UUID storeId,
                                             @RequestBody FoodRequestDto requestDto) {
        return foodService.createFood(storeId, requestDto, userDetails.getUser());
    }

    /**
     * 음식 목록 조회
     *
     * @param storeId 가게 아이디
     * @return 가게 음식 목록 정보
     */
    @GetMapping
    public ResponseEntity<List<FoodResponseDto>> getFoodList(@PathVariable UUID storeId,
                                                             @RequestParam(defaultValue = "추천") String foodTagName) {
        return foodService.getFoodList(storeId, foodTagName);
    }

    /**
     * 음식 단일 조회
     *
     * @param storeId 가게 아이디
     * @param foodId  음식 아이디
     * @return 선택 음식 상세 정보
     */
    @GetMapping("/{foodId}")
    public ResponseEntity<FoodResponseDto> getFood(@PathVariable UUID storeId, @PathVariable UUID foodId) {
        return foodService.getFood(storeId, foodId);
    }

    /**
     * 음식 수정
     *
     * @param userDetails 사용자 정보
     * @param storeId     가게 아이디
     * @param foodId      음식 아이디
     * @param requestDto  수정할 음식 정보 양식
     * @return 요청 성공 메세지
     */
    @PatchMapping("/{foodId}")
    public ResponseEntity<String> updateFood(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                             @PathVariable UUID storeId,
                                             @PathVariable UUID foodId,
                                             @RequestBody FoodRequestDto requestDto) {
        return foodService.updateFood(storeId, foodId, requestDto, userDetails.getUser());
    }

    /**
     * 음식 삭제
     *
     * @param userDetails 사용자 정보
     * @param storeId     가게 아이디
     * @param foodId      음식 아이디
     * @return 요청 성공 메세지
     */
    @DeleteMapping("/{foodId}")
    public ResponseEntity<String> deleteFood(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                             @PathVariable UUID storeId,
                                             @PathVariable UUID foodId) {
        return foodService.deleteFood(storeId, foodId, userDetails.getUser());
    }
}
