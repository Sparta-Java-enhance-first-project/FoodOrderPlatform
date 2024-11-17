package com.example.foodorderplatform.dto;

import com.example.foodorderplatform.entity.FoodCart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CartInfoResponseDto {
    private UUID cartId;
    private List<FoodCart> foodList;
    private String address;
}
