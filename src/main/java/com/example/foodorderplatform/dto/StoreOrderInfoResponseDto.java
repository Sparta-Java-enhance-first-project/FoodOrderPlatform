package com.example.foodorderplatform.dto;

import com.example.foodorderplatform.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class StoreOrderInfoResponseDto {
    private List<Order> orderList;

    public StoreOrderInfoResponseDto(List<Order> orderList) {
        this.orderList = orderList;
    }

}
