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
    CREATE_CARTINFO_SUCCESS("음식이 장바구니에 담겼습니다."),
    UPDATE_FOODCOUNT_SUCCESS("음식의 개수를 변경했습니다."),
    DELETE_FOODCOUNT_SUCCESS("음식을 장바구니에서 삭제했습니다."),
    DELETE_CART_SUCCESS("장바구니를 삭제했습니다."),
    CREATE_ORDER_SUCCESS("주문이 정상적으로 접수되었습니다."),
    CREATE_ORDER_FAIL("주문이 실패했습니다."),
    UPDATE_ORDER_SUCCESS("주문을 수정하였습니다."),
    UPDATE_ORDER_FAIL("주문을 수정할 수 없습니다."),
    DELETE_ORDER_SUCCESS("주문을 취소하였습니다."),
    DELETE_ORDER_FAIL("주문을 취소할 수 없습니다."),
    UPDATE_FOOD_TAG_SUCCESS("음식 태그 수정이 완료되었습니다.");
    private final String message;

    SuccessMessage(String message){
        this.message = message;
    }

}
