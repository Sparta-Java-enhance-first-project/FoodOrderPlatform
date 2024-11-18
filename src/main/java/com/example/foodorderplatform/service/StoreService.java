package com.example.foodorderplatform.service;

import static com.example.foodorderplatform.message.ExceptionMessage.USER_UNAUTHORIZED;
import static com.example.foodorderplatform.message.SuccessMessage.STORE_ENTER_REQUEST_SUCCESS;

import com.example.foodorderplatform.dto.StoreCreateRequestDto;
import com.example.foodorderplatform.dto.StoreCreateResponseDto;
import com.example.foodorderplatform.dto.StoreInfoResponseDto;
import com.example.foodorderplatform.entity.Address;
import com.example.foodorderplatform.entity.BusinessInfo;
import com.example.foodorderplatform.entity.Region;
import com.example.foodorderplatform.entity.Store;
import com.example.foodorderplatform.entity.User;
import com.example.foodorderplatform.enumclass.StoreConfirmStatus;
import com.example.foodorderplatform.message.ExceptionMessage;
import com.example.foodorderplatform.message.SuccessMessage;
import com.example.foodorderplatform.repository.AddressRepository;
import com.example.foodorderplatform.repository.BusinessInfoRepository;
import com.example.foodorderplatform.repository.RegionRepository;
import com.example.foodorderplatform.repository.StoreRepository;
import com.example.foodorderplatform.repository.UserRepository;
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
public class StoreService {

    // 시큐리티 도입 후 삭제, 임시
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final BusinessInfoRepository businessInfoRepository;
    private final RegionRepository regionRepository;
    private final AddressRepository addressRepository;

    // 가게 생성 요청
    public ResponseEntity<String> createStoreEnterRequest(StoreCreateRequestDto storeCreateRequestDto, User user) {
        // 지역 생성 or 조회
        Region region = regionRepository.findByRegionName(storeCreateRequestDto.getRegionName()).orElse(null);
        if (region == null){
            region = regionRepository.save(new Region(storeCreateRequestDto.getRegionName()));
        }

        // 상세 주소 생성
        Address address = addressRepository.save(new Address(storeCreateRequestDto.getAddressName()));

        // 사업자 생성
        BusinessInfo businessInfo = businessInfoRepository.save(new BusinessInfo(storeCreateRequestDto.getBusinessInfoDto()));

        // 가게 생성
        storeRepository.save(new Store(user, region, address, storeCreateRequestDto, businessInfo));
        return new ResponseEntity<>(STORE_ENTER_REQUEST_SUCCESS.getMessage(), HttpStatus.OK);
    }

    // 가게 생성 요청 목록 조회
    public ResponseEntity<List<StoreCreateResponseDto>> getStoreEnterRequestList(User user) {
        if (!UserValidator.validateRoleUpperManager(user)){
            throw new IllegalArgumentException(USER_UNAUTHORIZED.getMessage());
        }
        List<Store> storeList = storeRepository.findAllByConfirmStatusAndDeletedAtIsNull(StoreConfirmStatus.REQUIRED);
        List<StoreCreateResponseDto> storeCreateResponseDtoList = storeList.stream().map(StoreCreateResponseDto::new).toList();
        return new ResponseEntity<>(storeCreateResponseDtoList, HttpStatus.OK);
    }

    // 가게 생성 요청 단일 조회
    public ResponseEntity<StoreCreateResponseDto> getStoreEnterRequest(UUID storeId, User user) {
        if (!UserValidator.validateRoleUpperManager(user)){
            throw new IllegalArgumentException(USER_UNAUTHORIZED.getMessage());
        }
        Store store = findStoreById(storeId);
        return new ResponseEntity<>(new StoreCreateResponseDto(store), HttpStatus.OK);
    }

    // 가게 생성 요청 승인
    public ResponseEntity<String> confirmStoreEnterRequest(UUID storeId, User user) {
        if (!UserValidator.validateRoleUpperManager(user)){
            throw new IllegalArgumentException(USER_UNAUTHORIZED.getMessage());
        }
        Store store = findStoreById(storeId);
        store.enterConfirm();
        return new ResponseEntity<>(SuccessMessage.STORE_ENTER_REQUEST_CONFIRMED.getMessage(), HttpStatus.OK);
    }

    // 가게 생성 요청 거부
    public ResponseEntity<String> rejectStoreEnterRequest(UUID storeId, User user) {
        if (!UserValidator.validateRoleUpperManager(user)){
            throw new IllegalArgumentException(USER_UNAUTHORIZED.getMessage());
        }
        Store store = findStoreById(storeId);
        store.enterReject();
        store.setDeletedByUser(user.getUserName());
        return new ResponseEntity<>(SuccessMessage.STORE_ENTER_REQUEST_REJECTED.getMessage(), HttpStatus.OK);
    }

    // 가게 목록 조회
    public ResponseEntity<List<StoreInfoResponseDto>> getStoreList(String storeCategoryName) {
        List<Store> storeList;
        if (storeCategoryName.equals("전체")){
            storeList = storeRepository.findAllByDeletedAtIsNull();
        } else {
            storeList = storeRepository.findAllByDeletedAtIsNullAndStoreCategory_StoreCategoryName(storeCategoryName);
        }
        List<StoreInfoResponseDto> storeInfoResponseDtoList = storeList.stream().map(StoreInfoResponseDto::new).toList();
        return new ResponseEntity<>(storeInfoResponseDtoList, HttpStatus.OK);
    }

    // 가게 단일 조회
    public ResponseEntity<StoreInfoResponseDto> getStore(UUID storeId) {
        Store store = findStoreById(storeId);
        return new ResponseEntity<>(new StoreInfoResponseDto(store), HttpStatus.OK);
    }
    /*
    ------------------------------------------------[private]--------------------------------------------------------
     */
    private Store findStoreById(UUID storeId){
        return storeRepository.findById(storeId).orElseThrow(
                () -> new IllegalArgumentException(ExceptionMessage.STORE_NOT_FOUND.getMessage())
        );
    }
}
