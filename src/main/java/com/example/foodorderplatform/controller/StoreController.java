package com.example.foodorderplatform.controller;

import com.example.foodorderplatform.dto.StoreCreateRequestDto;
import com.example.foodorderplatform.dto.StoreCreateResponseDto;
import com.example.foodorderplatform.dto.StoreInfoResponseDto;
import com.example.foodorderplatform.security.UserDetailsImpl;
import com.example.foodorderplatform.service.StoreService;
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
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;

    /**
     * 가개 개설 요청
     * @param storeCreateRequestDto : 가게 개설 정보 입력 양식
     * @return 요청 완료 메세지
     */
    @PostMapping("/request")
    public ResponseEntity<String> createStoreEnterRequest(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                          @RequestBody StoreCreateRequestDto storeCreateRequestDto){
        return storeService.createStoreEnterRequest(storeCreateRequestDto, userDetails.getUser());
    }

    /**
     * 가게 개설 요청 목록 조회
     * @return 가게 개설 요청 목록
     */
    @GetMapping("/request")
    public ResponseEntity<List<StoreCreateResponseDto>> getStoreEnterRequestList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return storeService.getStoreEnterRequestList(userDetails.getUser());
    }

    /**
     * 가게 개설 요청 목록 조회
     * @return 가게 개설 요청 목록
     */
    @GetMapping("/request/{storeId}")
    public ResponseEntity<StoreCreateResponseDto> getStoreEnterRequest(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                       @PathVariable UUID storeId){
        return storeService.getStoreEnterRequest(storeId, userDetails.getUser());
    }

    /**
     * 가게 개설 요청 승인
     * @param userDetails 사용자 정보
     * @param storeId 가게 아이디
     * @return 요청 성공 메세지
     */
    @PatchMapping("/request/{storeId}")
    public ResponseEntity<String> confirmStoreEnterRequest(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                           @PathVariable UUID storeId){
        return storeService.confirmStoreEnterRequest(storeId, userDetails.getUser());
    }

    /**
     * 가게 개설 요청 거부
     * @param userDetails 사용자 정보
     * @param storeId 가게 아이디
     * @return 요청 성공 메세지
     */
    @DeleteMapping("/request/{storeId}")
    public ResponseEntity<String> rejectStoreEnterRequest(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                          @PathVariable UUID storeId){
        return storeService.rejectStoreEnterRequest(storeId, userDetails.getUser());
    }

    /**
     * 가게 목록 조회
     * @param storeCategoryName 가게 카테고리 이름
     * @return 가게 목록
     */
    @GetMapping
    public ResponseEntity<List<StoreInfoResponseDto>> getStoreList(@RequestParam(defaultValue = "전체") String storeCategoryName){
        return storeService.getStoreList(storeCategoryName);
    }

    /**
     * 가게 단일 조회
     * 가게 조회 시 하단 음식 정보는 foodController에서 처리
     * @param storeId 조회할 가게 아이디
     * @return 조회한 가게 정보
     */
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreInfoResponseDto> getStore(@PathVariable UUID storeId){
        return storeService.getStore(storeId);
    }
}
