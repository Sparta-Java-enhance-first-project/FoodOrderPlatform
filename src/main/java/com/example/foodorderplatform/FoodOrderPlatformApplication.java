package com.example.foodorderplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class FoodOrderPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodOrderPlatformApplication.class, args);
    }

}
