package com.example.foodorderplatform.service;

import static com.example.foodorderplatform.message.ExceptionMessage.Food_NOT_FOUND;
import static com.example.foodorderplatform.message.ExceptionMessage.STORE_NOT_FOUND;
import static com.example.foodorderplatform.message.SuccessMessage.CREATE_FOOD_SUCCESS;
import static com.example.foodorderplatform.message.SuccessMessage.UPDATE_FOOD_SUCCESS;

import com.example.foodorderplatform.dto.FoodRequestDto;
import com.example.foodorderplatform.dto.FoodResponseDto;
import com.example.foodorderplatform.entity.Food;
import com.example.foodorderplatform.entity.FoodTag;
import com.example.foodorderplatform.entity.Store;
import com.example.foodorderplatform.repository.FoodRepository;
import com.example.foodorderplatform.repository.FoodTagRepository;
import com.example.foodorderplatform.repository.StoreRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodService {

    private final StoreRepository storeRepository;
    private final FoodRepository foodRepository;
    private final FoodTagRepository foodTagRepository;

    public ResponseEntity<String> createFood(UUID storeId, FoodRequestDto requestDto) {
        Store store = findStoreById(storeId);
        FoodTag foodTag = findFoodTagByFoodTagName(requestDto.getFoodTagName());
        foodRepository.save(new Food(store, foodTag, requestDto));
        return new ResponseEntity<>(CREATE_FOOD_SUCCESS.getMessage(), HttpStatus.OK);
    }

    public ResponseEntity<List<FoodResponseDto>> getFoodList(UUID storeId) {
        List<Food> foodList = foodRepository.findAllByStore_Id(storeId);
        List<FoodResponseDto> foodResponseDtoList = foodList.stream().map(FoodResponseDto::new).toList();
        return new ResponseEntity<>(foodResponseDtoList, HttpStatus.OK);
    }

    public ResponseEntity<FoodResponseDto> getFood(UUID storeId, UUID foodId) {
        Store store = findStoreById(storeId);
        Food food = findFoodById(foodId);
        return new ResponseEntity<>(new FoodResponseDto(food), HttpStatus.OK);
    }

    public ResponseEntity<String> updateFood(UUID storeId, UUID foodId, FoodRequestDto requestDto) {
        Store store = findStoreById(storeId);
        Food food = findFoodById(foodId);
        FoodTag foodTag = foodTagRepository.findByFoodTagName(requestDto.getFoodTagName());
        food.updateFood(foodTag, requestDto);
        return new ResponseEntity<>(UPDATE_FOOD_SUCCESS.getMessage(), HttpStatus.OK);
    }

    /*
    ---------------------------------------- [private 메서드] -------------------------------------------
     */
    private Store findStoreById(UUID storeId){
        return storeRepository.findById(storeId).orElseThrow(
                () -> new IllegalArgumentException(STORE_NOT_FOUND.getMessage())
        );
    }

    private Food findFoodById(UUID foodId){
        return foodRepository.findById(foodId).orElseThrow(
                () -> new IllegalArgumentException(Food_NOT_FOUND.getMessage())
        );
    }

    private FoodTag findFoodTagByFoodTagName(String foodTagName){
        return foodTagRepository.findByFoodTagName(foodTagName);
    }
}
