package com.example.foodorderplatform.service;


import com.example.foodorderplatform.dto.CartInfoResponseDto;
import com.example.foodorderplatform.dto.FoodCartRequestDto;
import com.example.foodorderplatform.entity.*;
import com.example.foodorderplatform.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.example.foodorderplatform.message.ErrorMessage.*;
import static com.example.foodorderplatform.message.SuccessMessage.*;

@Slf4j(topic = "Cart Service")
@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final FoodCartRepository foodCartRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public ResponseEntity<CartInfoResponseDto> getCartInfo(Long userId) {
        Cart cart = cartRepository.findByUser_IdAndDeletedAtIsNull(userId).orElse(null);

        if (cart == null) {
            return ResponseEntity.ok().build();
        }

        List<FoodCart> foodCartList = foodCartRepository.findAllByCart_IdAndDeletedAtIsNull(cart.getId());

        String userAddress;
        if(userRepository.findById(userId).isPresent()){
            userAddress = userRepository.findById(userId).get().getAddress().getAddressName();
        }else{
            userAddress ="";
        }

        CartInfoResponseDto cartInfoResponseDto = new CartInfoResponseDto();
        cartInfoResponseDto.setFoodList(foodCartList);
        cartInfoResponseDto.setAddress(userAddress);

        return new ResponseEntity<>(cartInfoResponseDto, HttpStatus.OK);
    }

    public ResponseEntity<String> addFoodToCart(UUID storeId, UUID foodId, Long userId, int foodAddCount) {
        String responseMessage = "";
        try{
            User user = userRepository.findById(userId).orElse(null);
            Store store = storeRepository.findById(storeId).orElse(null);
            Food food = foodRepository.findById(foodId).orElse(null);

            Cart cart = cartRepository.findByUser_idAndStore_id(user.getId(), store.getId()).orElse(new Cart(user, store));
            FoodCart foodCart = foodCartRepository.findByFood_idAnAndCart_idAndDeletedAtIsNull(food.getId(), cart.getId())
                    .orElse(new FoodCart(cart, food));

            foodCart.setFoodCnt(foodCart.getFoodCnt() + foodAddCount);

            cartRepository.save(cart);
            foodCartRepository.save(foodCart);

            responseMessage = CREATE_CARTINFO_SUCCESS.getMessage();
        } catch (Exception e) {
            responseMessage = ADD_FOOD_ERROR.getMessage();
        }

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    public ResponseEntity<String> updateFoodCount(UUID cartId, FoodCartRequestDto foodCartRequestDto) {
        String responseMessage;
        UUID foodId = foodCartRequestDto.getFoodId();
        int foodCount = foodCartRequestDto.getFoodCount();
        try {
            FoodCart foodCart = foodCartRepository.findByFood_idAnAndCart_idAndDeletedAtIsNull(foodId, cartId).orElse(null);
            foodCart.setFoodCnt(foodCount);

            responseMessage = UPDATE_FOODCOUNT_SUCCESS.getMessage();
        } catch (Exception e) {
            responseMessage = UPDATE_FOODCOUNT_ERROR.getMessage();
        }

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteFoodCount(UUID cartId, FoodCartRequestDto foodCartRequestDto, String userName) {
        String responseMessage;
        UUID foodId = foodCartRequestDto.getFoodId();
        int foodCount = foodCartRequestDto.getFoodCount();
        try {
            FoodCart foodCart = foodCartRepository.findByFood_idAnAndCart_idAndDeletedAtIsNull(foodId, cartId).orElse(null);
            foodCart.setFoodCnt(foodCount);
            foodCart.setDeletedAt(LocalDateTime.now());
            foodCart.setDeletedBy(userName);

            responseMessage = DELETE_FOODCOUNT_SUCCESS.getMessage();
            foodCart.setDeletedAt(LocalDateTime.now());
        } catch (Exception e) {
            responseMessage = DELETE_FOODCOUNT_ERROR.getMessage();
        }

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


    public ResponseEntity<String> deleteCart(UUID cartId, String userName) {
        String responseMessage;
        LocalDateTime now = LocalDateTime.now();

        try {
            Cart cart = cartRepository.findById(cartId).orElse(null);

            cart.setDeletedAt(now);
            cart.setDeletedBy(userName);

            List<FoodCart> foodCartList = foodCartRepository.findAllByCart_IdAndDeletedAtIsNull(cart.getId());

            for (FoodCart foodCart : foodCartList) {
                foodCart.setFoodCnt(0);
                foodCart.setDeletedAt(now);
                foodCart.setDeletedBy(userName);
            }

            responseMessage = DELETE_CART_SUCCESS.getMessage();
        } catch (Exception e) {
            responseMessage = DELETE_CART_ERROR.getMessage();
        }

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
