package com.example.foodorderplatform.repository;

import com.example.foodorderplatform.entity.Cart;
import com.example.foodorderplatform.entity.FoodCart;
import com.example.foodorderplatform.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByUser_IdAndDeletedAtIsNull(Long userId);

    Optional<Cart> findByUser_idAndStore_id(Long userId, UUID storeId);

    Optional<Cart> findByIdAndDeletedAtIsNull(UUID cartId);

    //List<Cart> findAllByCart_IdAndDeletedAtIsNull(UUID cartId);
}
