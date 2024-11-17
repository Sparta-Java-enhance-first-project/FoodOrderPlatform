package com.example.foodorderplatform.dto;

import com.example.foodorderplatform.enumclass.BankEnum;
import com.example.foodorderplatform.enumclass.OrderTypeEnum;
import com.example.foodorderplatform.enumclass.ReceiveTypeEnum;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class OrderRequestDto {
    private UUID cartId;
    private List<FoodCartRequestDto> foodCarRequestList;
    private String address;
    private String orderRequest;
    private BankEnum bank;
    private Long orderPrice;
    private OrderTypeEnum orderType;
    private ReceiveTypeEnum receiveType;

}
