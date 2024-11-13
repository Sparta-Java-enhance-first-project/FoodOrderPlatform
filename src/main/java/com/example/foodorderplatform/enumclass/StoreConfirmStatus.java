package com.example.foodorderplatform.enumclass;

import lombok.Getter;

@Getter
public enum StoreConfirmStatus {

    REQUIRED("요청 중"),
    CONFIRMED("승인"),
    REJECTED("거부");


    private String confirmStatus;

    StoreConfirmStatus(String confirmStatus){
        this.confirmStatus = confirmStatus;
    }

}
