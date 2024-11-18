package com.example.foodorderplatform.repository;

import com.example.foodorderplatform.entity.AiScript;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AIScriptRepository extends JpaRepository<AiScript, UUID> {
}
