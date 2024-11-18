package com.example.foodorderplatform.dto;

import com.example.foodorderplatform.entity.FoodCart;
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
public class StoreOrderInfoResponseDto {
    private List<StoreOrderInfoResponseDtoBuilder> storeOrderList;

    public void setStoreOrderList(List<Order> foodCartListList) {
        this.storeOrderList = foodCartListList.stream().map(StoreOrderInfoResponseDtoBuilder::new).toList();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    private static class StoreOrderInfoResponseDtoBuilder {
        private UUID orderId;
        private LocalDateTime createdAt;
        private OrderTypeEnum orderType;
        private ReceiveTypeEnum receiveType;
        private OrderStatusEnum orderStatus;
        private Long orderPrice ;
        private List<Map<String, Object>> foodList = new ArrayList<>();
        private StoreOrderInfoResponseDtoBuilder(Order order) {
//            this.orderId = order.getId();
//            this.createdAt = order.getCreateAt();
//            this.orderType = order.getOrderType();
//            this.receiveType = order.getReceiveType();
//            this.orderStatus = order.getOrderStatus();
//            this.orderPrice = order.getOrderPrice();
//            for (FoodOrder foodOrder : order.getFoodOrderList()) {
//                Map<String, Object> foodMap = new HashMap<String, Object>();
//                foodMap.put("foodName", foodOrder.getFood().getFoodName());
//                foodMap.put("count", foodOrder.getFoodCnt());
//                foodList.add(foodMap);
//            }
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
