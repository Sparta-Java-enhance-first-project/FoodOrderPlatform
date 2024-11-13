package com.example.foodorderplatform.controller;

import com.example.foodorderplatform.dto.SignupRequestDto;
import com.example.foodorderplatform.dto.UserInfoRequestDto;
import com.example.foodorderplatform.dto.UserInfoResponseDto;
import com.example.foodorderplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    /**
     * 회원 가입
     * @param requestDto : 회원가입 정보
     * @return : 회원 가입 완료 메세지
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequestDto requestDto){
        return userService.signup(requestDto);
    }

    /**
     * 마이 페이지 유저 정보 조회
     * @return 마이페이지
     */
    @GetMapping("/mypage/info")// 토큰에서 유저 식별 후 해당 유저 반환
    public ResponseEntity<UserInfoResponseDto> getUserInfo(){
        return userService.getUserInfo();
    }

    /**
     * 마이 페이지 유저 정보 수정
     * @param requestDto 수정된 유저 정보
     * @return 수정된 유저 정보
     */
    @PatchMapping("/mypage/info")
    public ResponseEntity<UserInfoResponseDto> updateUserInfo(@RequestBody UserInfoRequestDto requestDto){
        return userService.updateUserInfo(requestDto);
    }



}
