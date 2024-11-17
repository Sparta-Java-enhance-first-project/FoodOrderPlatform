package com.example.foodorderplatform.repository;

import com.example.foodorderplatform.entity.FoodTag;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTagRepository extends JpaRepository<FoodTag, UUID> {
    FoodTag findByFoodTagName(String foodTagName);
    List<FoodTag> findAllByStore_Id(UUID storeId);
}
