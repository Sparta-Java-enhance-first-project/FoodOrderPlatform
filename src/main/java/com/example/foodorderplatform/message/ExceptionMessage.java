package com.example.foodorderplatform.message;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.Getter;

@Getter
public enum ExceptionMessage {
    STORE_NOT_FOUND("가게를 찾을 수 없습니다.")
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
