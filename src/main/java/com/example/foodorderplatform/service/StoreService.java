package com.example.foodorderplatform.service;

import static com.example.foodorderplatform.message.SuccessMessage.CREATE_STORE_REQUEST_SUCCESS;

import com.example.foodorderplatform.dto.StoreCreateRequestDto;
import com.example.foodorderplatform.dto.StoreCreateResponseDto;
import com.example.foodorderplatform.entity.Address;
import com.example.foodorderplatform.entity.BusinessInfo;
import com.example.foodorderplatform.entity.Region;
import com.example.foodorderplatform.entity.Store;
import com.example.foodorderplatform.entity.User;
import com.example.foodorderplatform.enumclass.StoreConfirmStatus;
import com.example.foodorderplatform.message.ExceptionMessage;
import com.example.foodorderplatform.repository.AddressRepository;
import com.example.foodorderplatform.repository.BusinessInfoRepository;
import com.example.foodorderplatform.repository.RegionRepository;
import com.example.foodorderplatform.repository.StoreRepository;
import com.example.foodorderplatform.repository.UserRepository;
import java.util.List;
import java.util.Optional;
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


    public ResponseEntity<String> createStoreEnterRequest(StoreCreateRequestDto storeCreateRequestDto) {
        //todo 임시, 추후 시큐리티 도입 후 userDetails를 통해 가져오도록 변경
        User user = userRepository.findByUserName("yshong1998").get();
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
        return new ResponseEntity<>(CREATE_STORE_REQUEST_SUCCESS.getMessage(), HttpStatus.OK);
    }

    public ResponseEntity<List<StoreCreateResponseDto>> getStoreEnterRequestList() {
        List<Store> storeList = storeRepository.findAllByConfirmStatus(StoreConfirmStatus.REQUIRED);
        List<StoreCreateResponseDto> storeCreateResponseDtoList = storeList.stream().map(StoreCreateResponseDto::new).toList();
        return new ResponseEntity<>(storeCreateResponseDtoList, HttpStatus.OK);
    }

    public ResponseEntity<StoreCreateResponseDto> getStoreEnterRequest(UUID storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new IllegalArgumentException(ExceptionMessage.STORE_NOT_FOUND.getMessage())
        );
        return new ResponseEntity<>(new StoreCreateResponseDto(store), HttpStatus.OK);

    }
}
