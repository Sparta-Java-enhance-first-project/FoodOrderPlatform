package com.example.foodorderplatform.repository;

import com.example.foodorderplatform.entity.BusinessInfo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessInfoRepository extends JpaRepository<BusinessInfo, UUID> {
}
