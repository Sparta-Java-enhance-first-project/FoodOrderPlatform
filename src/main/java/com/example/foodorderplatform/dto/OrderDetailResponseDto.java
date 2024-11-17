package com.example.foodorderplatform.dto;

import com.example.foodorderplatform.entity.FoodOrder;
import com.example.foodorderplatform.entity.Order;

import java.util.List;

public class OrderDetailResponseDto {

    private Order order;
    private List<FoodOrder> foodOrders;

    public OrderDetailResponseDto(Order order, List<FoodOrder> foodOrders) {
        this.order = order;
        this.foodOrders = foodOrders;
    }
}
