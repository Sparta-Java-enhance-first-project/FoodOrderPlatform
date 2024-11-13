package com.example.foodorderplatform.dto;

import com.example.foodorderplatform.entity.Food;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FoodResponseDto {

    private UUID foodId;
    private String foodName;	// 음식 이름
    private String foodDesc;	// 음식 설명
//    private String foodImage;	// 음식 이미지
    private Long foodPrice;	// 음식 가격
    private Boolean foodState;	// 주문가능여부
    private Boolean recommand;	// 추천
    private String foodTageName; // 음식 태그 이름

    public FoodResponseDto(Food food){
        this.foodId = food.getId();
        this.foodName = food.getFoodName();
        this.foodDesc = food.getFoodDesc();
//        this.foodImage = food.getFoodImage();
        this.foodPrice = food.getFoodPrice();
        this.foodState = food.getFoodState();
        this.recommand = food.getRecommand();
        this.foodTageName = food.getFoodTag().getFoodTagName();
    }
}
