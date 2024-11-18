package com.example.foodorderplatform.service;

import static com.example.foodorderplatform.message.ExceptionMessage.FOOD_TAG_NOT_FOUND;
import static com.example.foodorderplatform.message.ExceptionMessage.STORE_NOT_FOUND;
import static com.example.foodorderplatform.message.ExceptionMessage.USER_UNAUTHORIZED;
import static com.example.foodorderplatform.message.SuccessMessage.CREATE_FOOD_TAG_SUCCESS;
import static com.example.foodorderplatform.message.SuccessMessage.UPDATE_FOOD_TAG_SUCCESS;

import com.example.foodorderplatform.dto.FoodTagRequestDto;
import com.example.foodorderplatform.dto.FoodTagResponseDto;
import com.example.foodorderplatform.entity.FoodTag;
import com.example.foodorderplatform.entity.Store;
import com.example.foodorderplatform.entity.User;
import com.example.foodorderplatform.message.SuccessMessage;
import com.example.foodorderplatform.repository.FoodTagRepository;
import com.example.foodorderplatform.repository.StoreRepository;
import com.example.foodorderplatform.util.UserValidator;
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
public class FoodTagService {
    private final StoreRepository storeRepository;
    private final FoodTagRepository foodTagRepository;

    // 생성
    public ResponseEntity<String> createFoodTag(UUID storeId, FoodTagRequestDto requestDto, User user) {
        Store store = findStoreById(storeId);
        if (!UserValidator.validateRoleUpperManager(user) || !UserValidator.validateIsStoreOwner(store, user)){
            throw new IllegalArgumentException(USER_UNAUTHORIZED.getMessage());
        }
        foodTagRepository.save(new FoodTag(store, requestDto.getFoodTagName()));
        return new ResponseEntity<>(CREATE_FOOD_TAG_SUCCESS.getMessage(), HttpStatus.OK);
    }

    // 목록 조회
    public ResponseEntity<List<FoodTagResponseDto>> getFoodTagList(UUID storeId) {
        List<FoodTag> foodTagList = foodTagRepository.findAllByStore_Id(storeId);
        List<FoodTagResponseDto> foodTagResponseDtoList = foodTagList.stream().map(FoodTagResponseDto::new).toList();
        return new ResponseEntity<>(foodTagResponseDtoList, HttpStatus.OK);
    }

    // 수정
    public ResponseEntity<String> updateFoodTag(UUID storeId, UUID foodTagId, FoodTagRequestDto requestDto, User user) {
        Store store = findStoreById(storeId);
        if (!UserValidator.validateIsStoreOwner(store, user)){
            throw new IllegalArgumentException(USER_UNAUTHORIZED.getMessage());
        }
        FoodTag foodTag = findFoodTagById(foodTagId);
        foodTag.updateFoodTagName(requestDto.getFoodTagName());
        return new ResponseEntity<>(UPDATE_FOOD_TAG_SUCCESS.getMessage(), HttpStatus.OK);
    }

    // 삭제
    public ResponseEntity<String> deleteFoodTag(UUID storeId, UUID foodTagId, User user) {
        Store store = findStoreById(storeId);
        if (!UserValidator.validateRoleUpperManager(user) || !UserValidator.validateIsStoreOwner(store, user)){
            throw new IllegalArgumentException(USER_UNAUTHORIZED.getMessage());
        }
        FoodTag foodTag = findFoodTagById(foodTagId);
        foodTag.setDeletedByUser(user.getUserName());
        return new ResponseEntity<>(SuccessMessage.DELETE_FOOD_TAG_SUCCESS.getMessage(), HttpStatus.OK);
    }

    /*
    ---------------------------------- [private 메서드] ----------------------------------------------
     */
    private Store findStoreById(UUID storeId){
        return storeRepository.findById(storeId).orElseThrow(
                () -> new IllegalArgumentException(STORE_NOT_FOUND.getMessage())
        );
    }

    private FoodTag findFoodTagById(UUID foodTagId){
        return foodTagRepository.findById(foodTagId).orElseThrow(
                () -> new IllegalArgumentException(FOOD_TAG_NOT_FOUND.getMessage())
        );
    }
}
