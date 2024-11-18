package com.example.foodorderplatform.message;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    ADD_FOOD_ERROR("음식을 추가할 수 없습니다."),
    UPDATE_FOODCOUNT_ERROR("음식 개수를 수정할 수 없습니다."),
    DELETE_FOODCOUNT_ERROR("음식을 장바구니에서 삭제할 수 없습니다."),
    DELETE_CART_ERROR("장바구니를 삭제할 수 없습니다."),
    CREATE_PAYMENT_ERROR("결제가 실패했습니다."),
    CREATE_ORDER_ERROR("주문이 정상적으로 처리되지 않았습니다."),
    UPDATE_ORDER_ERROR("주문을 수정하지 못 했습니다."),
    DELETE_ORDER_ERROR("주문을 취소하지 못 했습니다."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
