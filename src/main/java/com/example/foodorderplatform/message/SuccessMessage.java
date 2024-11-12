package com.example.foodorderplatform.message;

import lombok.Getter;

@Getter
public enum SuccessMessage {
    CREATE_STORE_REQUEST_SUCCESS("가개 개설 요청이 완료되었습니다."),
    SIGNUP_SUCCESS("회원가입이 완료되었습니다.");
    private final String message;

    SuccessMessage(String message){
        this.message = message;
    }

}
