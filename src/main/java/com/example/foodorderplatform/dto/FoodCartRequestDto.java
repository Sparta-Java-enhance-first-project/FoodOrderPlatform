package com.example.foodorderplatform.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class FoodCartRequestDto {
    private UUID foodId;
    private int foodCount;

    public FoodCartRequestDto(UUID foodId) {
        this.foodId = foodId;
        this.foodCount = 0;
    }

}
