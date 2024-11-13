package com.example.foodorderplatform.service;

import static com.example.foodorderplatform.message.ExceptionMessage.STORE_NOT_FOUND;
import static com.example.foodorderplatform.message.SuccessMessage.CREATE_FOOD_TAG_SUCCESS;

import com.example.foodorderplatform.dto.FoodTagRequestDto;
import com.example.foodorderplatform.entity.FoodTag;
import com.example.foodorderplatform.entity.Store;
import com.example.foodorderplatform.repository.FoodTagRepository;
import com.example.foodorderplatform.repository.StoreRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodTagService {
    private final StoreRepository storeRepository;
    private final FoodTagRepository foodTagRepository;

    public ResponseEntity<String> createFoodTag(UUID storeId, FoodTagRequestDto requestDto) {
        Store store = findStoreById(storeId);
        foodTagRepository.save(new FoodTag(store, requestDto.getFoodTagName()));
        return new ResponseEntity<>(CREATE_FOOD_TAG_SUCCESS.getMessage(), HttpStatus.OK);
    }

    /*
    ---------------------------------- [private 메서드] ----------------------------------------------
     */
    private Store findStoreById(UUID storeId){
        return storeRepository.findById(storeId).orElseThrow(
                () -> new IllegalArgumentException(STORE_NOT_FOUND.getMessage())
        );
    }

    private FoodTag findFoodTagByFoodTagName(String foodTagName){
        return foodTagRepository.findByFoodTagName(foodTagName);
    }
}
