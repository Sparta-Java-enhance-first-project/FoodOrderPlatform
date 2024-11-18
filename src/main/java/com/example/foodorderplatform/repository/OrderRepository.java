package com.example.foodorderplatform.repository;

import com.example.foodorderplatform.entity.FoodOrder;
import com.example.foodorderplatform.entity.Order;
import com.example.foodorderplatform.enumclass.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByUser_id(Long userId);

    //List<Order> findAllByOrder_id(UUID orderId);

    List<Order> findAllByStore_id(UUID storeId);

    List<Order> findAllByStore_idAndOrderStatus(UUID storeId, OrderStatusEnum orderStatusEnum);
}
