package com.example.foodorderplatform.repository;

import com.example.foodorderplatform.entity.Address;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
