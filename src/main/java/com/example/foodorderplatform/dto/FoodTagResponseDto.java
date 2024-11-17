package com.example.foodorderplatform.dto;

import com.example.foodorderplatform.entity.FoodTag;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodTagResponseDto {

    private UUID foodTagId;
    private String foodTagName;

    public FoodTagResponseDto(FoodTag foodTag){
        this.foodTagId = foodTag.getId();
        this.foodTagName = foodTag.getFoodTagName();
    }

}
