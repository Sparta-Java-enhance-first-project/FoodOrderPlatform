package com.example.foodorderplatform.repository;

import com.example.foodorderplatform.entity.Region;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, UUID> {
    Optional<Region> findByRegionName(String regionName);
}
