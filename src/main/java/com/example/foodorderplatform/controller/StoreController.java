package com.example.foodorderplatform.controller;

import com.example.foodorderplatform.dto.StoreRequestDto;
import com.example.foodorderplatform.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
     * @param storeRequestDto : 가게 개설 정보 입력 양식
     * @return 요청 완료 메세지
     */

    @PostMapping
    public ResponseEntity<String> requestCreateStore(@RequestBody StoreRequestDto storeRequestDto){
        return storeService.createStore(storeRequestDto);
    }

}
