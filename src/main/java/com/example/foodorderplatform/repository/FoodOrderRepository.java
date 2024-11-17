package com.example.foodorderplatform.repository;

import com.example.foodorderplatform.entity.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, UUID> {

    List<FoodOrder> findAllByOrder_id(UUID orderId);
}
