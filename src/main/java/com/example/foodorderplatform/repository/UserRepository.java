package com.example.foodorderplatform.repository;

import com.example.foodorderplatform.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
}
