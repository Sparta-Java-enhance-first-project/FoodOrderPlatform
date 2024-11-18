package com.example.foodorderplatform.dto;

import com.example.foodorderplatform.enumclass.OrderStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderUpdateRequestByStoreDto {
    private OrderStatusEnum status;
}
