package com.example.foodorderplatform.controller;

import com.example.foodorderplatform.dto.SignupRequestDto;
import com.example.foodorderplatform.dto.UserInfoRequestDto;
import com.example.foodorderplatform.dto.UserInfoResponseDto;
import com.example.foodorderplatform.security.UserDetailsImpl;
import com.example.foodorderplatform.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j(topic = "User")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            JSONObject errors= new JSONObject();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                String fieldErrorMessage = fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage();
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                log.error(fieldErrorMessage);
            }
            return new ResponseEntity<>(errors.toString(), HttpStatus.OK);
        }

        return userService.signup(requestDto);
    }


    /**
     * 마이 페이지 유저 정보 조회
     * @return 마이페이지
     */
    @GetMapping("/mypage/info")// 토큰에서 유저 식별 후 해당 유저 반환
    public ResponseEntity<UserInfoResponseDto> getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.getUserInfo(userDetails);
    }

    /**
     * 마이 페이지 유저 정보 수정
     * @param requestDto 수정된 유저 정보
     * @return 수정된 유저 정보
     */
    @PatchMapping("/mypage/info")
    public ResponseEntity<UserInfoResponseDto> updateUserInfo(@RequestBody UserInfoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.updateUserInfo(requestDto, userDetails);
    }



}
