package com.example.foodorderplatform.message;

import lombok.Getter;

@Getter
public enum SuccessMessage {
    STORE_ENTER_REQUEST_SUCCESS("가게 개설 요청이 완료되었습니다."),
    STORE_ENTER_REQUEST_CONFIRMED("가게 개설 요청이 승인되었습니다."),
    STORE_ENTER_REQUEST_REJECTED("가게 개설 요청이 거부되었습니다."),
    SIGNUP_SUCCESS("회원가입이 완료되었습니다."),
    CREATE_FOOD_SUCCESS("음식 생성이 완료되었습니다."),
    UPDATE_FOOD_SUCCESS("음식 수정이 완료되었습니다."),
    CREATE_FOOD_TAG_SUCCESS("음식 태그 생성이 완료되었습니다."),
    UPDATE_FOOD_TAG_SUCCESS("음식 태그 수정이 완료되었습니다.");
    private final String message;

    SuccessMessage(String message){
        this.message = message;
    }

}
