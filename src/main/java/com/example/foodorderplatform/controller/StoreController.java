package com.example.foodorderplatform.controller;

import com.example.foodorderplatform.dto.StoreCreateRequestDto;
import com.example.foodorderplatform.dto.StoreCreateResponseDto;
import com.example.foodorderplatform.security.UserDetailsImpl;
import com.example.foodorderplatform.service.StoreService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping
    public ResponseEntity<String> createStoreEnterRequest(@RequestBody StoreCreateRequestDto storeCreateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return storeService.createStoreEnterRequest(storeCreateRequestDto, userDetails);
    }

    /**
     * 가게 개설 요청 목록 조회
     * @return 가게 개설 요청 목록
     */
    @GetMapping
    public ResponseEntity<List<StoreCreateResponseDto>> getStoreEnterRequestList(){
        return storeService.getStoreEnterRequestList();
    }

    /**
     * 가게 개설 요청 목록 조회
     * @return 가게 개설 요청 목록
     */
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreCreateResponseDto> getStoreEnterRequest(@PathVariable UUID storeId){
        return storeService.getStoreEnterRequest(storeId);
    }

    @PatchMapping("/{storeId}")
    public ResponseEntity<String> confirmStoreEnterRequest(@PathVariable UUID storeId){
        return storeService.confirmStoreEnterRequest(storeId);
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<String> rejectStoreEnterRequest(@PathVariable UUID storeId){
        return storeService.rejectStoreEnterRequest(storeId);
    }
}
