package com.example.foodorderplatform.message;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
    USER_UNAUTHORIZED("인가되지 않은 사용자입니다."),
    STORE_NOT_FOUND("가게를 찾을 수 없습니다."),
    FOOD_NOT_FOUND("음식을 찾을 수 없습니다."),
    FOOD_TAG_NOT_FOUND("음식 태그를 찾을 수 없습니다.");


    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
