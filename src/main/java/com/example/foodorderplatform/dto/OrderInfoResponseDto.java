package com.example.foodorderplatform.dto;

import com.example.foodorderplatform.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class OrderInfoResponseDto {
    private List<Order> orderList;

    public OrderInfoResponseDto(List<Order> orderList) {
        this.orderList = orderList;
    }

}
