package com.example.foodorderplatform.dto;

import com.example.foodorderplatform.entity.FoodCart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class CartInfoResponseDto {

    private UUID cartId;
    private String address;
    private List<CartInfoResponseDtoBuilder> foodList;

    public void setFoodList(List<FoodCart> foodCartListList) {
        foodList = foodCartListList.stream().map(CartInfoResponseDtoBuilder::new).toList();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CartInfoResponseDtoBuilder {
//        private CartInfoResponseDto cartInfoResponseDto;
        private UUID foodId;
        private String foodName;
        private int count;

        public CartInfoResponseDtoBuilder(FoodCart foodCart) {
            this.foodId = foodCart.getId();
            this.foodName = foodCart.getFood().getFoodName();
            this.count = foodCart.getFoodCnt();
        }


    }
}
