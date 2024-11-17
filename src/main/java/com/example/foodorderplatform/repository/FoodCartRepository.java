package com.example.foodorderplatform.repository;

import com.example.foodorderplatform.entity.FoodCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FoodCartRepository extends JpaRepository<FoodCart, UUID> {
    Optional<FoodCart> findByFood_idAndCart_idAndDeletedAtIsNull(UUID foodId, UUID cartId);

    List<FoodCart> findAllByCart_IdAndDeletedAtIsNull(UUID cartId);
}
