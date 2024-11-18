package com.example.foodorderplatform.repository;

import com.example.foodorderplatform.entity.Store;
import com.example.foodorderplatform.enumclass.StoreConfirmStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, UUID> {
    List<Store> findAllByDeletedAtIsNull();
    List<Store> findAllByConfirmStatusAndDeletedAtIsNull(StoreConfirmStatus required);
    List<Store> findAllByDeletedAtIsNullAndStoreCategory_StoreCategoryName(String storeCategoryName);
}
