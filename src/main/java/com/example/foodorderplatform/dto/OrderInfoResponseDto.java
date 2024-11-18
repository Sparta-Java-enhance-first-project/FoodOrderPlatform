package com.example.foodorderplatform.dto;

import com.example.foodorderplatform.entity.FoodOrder;
import com.example.foodorderplatform.entity.Order;
import com.example.foodorderplatform.enumclass.OrderStatusEnum;
import com.example.foodorderplatform.enumclass.OrderTypeEnum;
import com.example.foodorderplatform.enumclass.ReceiveTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
public class OrderInfoResponseDto {
    private List<OrderInfoResponseDtoBuilder> orderList;

    public OrderInfoResponseDto(List<Order> orderList) {
        this.orderList = orderList.stream().map(OrderInfoResponseDtoBuilder::new).toList();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    private static class OrderInfoResponseDtoBuilder {
        private UUID orderId;
        private LocalDateTime createdAt;
        private OrderTypeEnum orderType;
        private ReceiveTypeEnum receiveType;
        private OrderStatusEnum orderStatus;
        private List<Map<String, Object>> foodList = new ArrayList<>();
        private Long orderPrice ;
        private OrderInfoResponseDtoBuilder(Order order) {
            this.orderId = order.getId();
            this.createdAt = order.getCreateAt();
            this.orderType = order.getOrderType();
            this.receiveType = order.getReceiveType();
            this.orderStatus = order.getOrderStatus();
            this.orderPrice = order.getOrderPrice();
            for (FoodOrder foodOrder : order.getFoodOrderList()) {
                Map<String, Object> foodMap = new HashMap<String, Object>();
                foodMap.put("foodName", foodOrder.getFood().getFoodName());
                foodMap.put("count", foodOrder.getFoodCnt());
                foodList.add(foodMap);
            }
        }

    }

}
