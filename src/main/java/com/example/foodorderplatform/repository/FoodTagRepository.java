package com.example.foodorderplatform.repository;

import com.example.foodorderplatform.entity.FoodTag;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTagRepository extends JpaRepository<FoodTag, UUID> {
    FoodTag findByFoodTagName(String foodTagName);
}
