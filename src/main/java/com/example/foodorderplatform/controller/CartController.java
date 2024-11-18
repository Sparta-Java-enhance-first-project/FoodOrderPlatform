package com.example.foodorderplatform.controller;

import com.example.foodorderplatform.dto.CartInfoResponseDto;
import com.example.foodorderplatform.dto.FoodCartRequestDto;
import com.example.foodorderplatform.security.UserDetailsImpl;
import com.example.foodorderplatform.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j(topic = "Cart Controller")
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;

    @GetMapping("/cart")
    public ResponseEntity<CartInfoResponseDto> getCartInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cartService.getCartInfo(userDetails.getUser().getId());
    }

    @PostMapping("/store/{storeId}/food/{foodId}/cart")
    public ResponseEntity<String> addFoodToCart(@PathVariable UUID storeId, @PathVariable UUID foodId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        int foodAddCount = 1;
        return cartService.addFoodToCart(storeId, foodId, userDetails.getUser().getId(), foodAddCount);
    }

    @PatchMapping("/cart/{cartId}")
    public ResponseEntity<String> updateFoodCount(@PathVariable UUID cartId,@RequestBody @Valid FoodCartRequestDto foodCartRequestDto) {
        return cartService.updateFoodCount(cartId, foodCartRequestDto);
    }

    @DeleteMapping("/cart/{cartId}/food/{foodId}")
    public ResponseEntity<String> deleteFood(@PathVariable UUID cartId, @PathVariable UUID foodId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        FoodCartRequestDto foodCartRequestDto = new FoodCartRequestDto(foodId);
        return cartService.deleteFoodCount(cartId, foodCartRequestDto, userDetails.getUser().getUserName());
    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable UUID cartId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cartService.deleteCart(cartId, userDetails.getUser().getUserName());
    }


}
