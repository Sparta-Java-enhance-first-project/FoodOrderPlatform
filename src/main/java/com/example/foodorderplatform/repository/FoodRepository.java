package com.example.foodorderplatform.repository;

import com.example.foodorderplatform.entity.Food;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, UUID> {

    List<Food> findAllByStore_Id(UUID storeId);
    void deleteById(UUID id);
    List<Food> findAllByStore_IdAndRecommand(UUID storeId, boolean recommand);

}
