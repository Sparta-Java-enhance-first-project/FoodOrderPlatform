package com.example.foodorderplatform.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FoodRequestDto {
    private String foodName;	// 음식 이름
    private String foodDesc;	// 음식 설명
//    private String foodImage;	// 음식 이미지, S3 연동 후 추가예정
    private Long foodPrice;	// 음식 가격
    private Boolean foodState;	// 주문가능여부
    private Boolean recommand;	// 추천
    private String foodTagName; // 태그 이름

}
