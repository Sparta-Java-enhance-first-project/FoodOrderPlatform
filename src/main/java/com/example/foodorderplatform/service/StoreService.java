package com.example.foodorderplatform.service;

import static com.example.foodorderplatform.message.SuccessMessage.CREATE_STORE_REQUEST_SUCCESS;

import com.example.foodorderplatform.dto.StoreRequestDto;
import com.example.foodorderplatform.entity.Address;
import com.example.foodorderplatform.entity.BusinessInfo;
import com.example.foodorderplatform.entity.Region;
import com.example.foodorderplatform.entity.Store;
import com.example.foodorderplatform.entity.User;
import com.example.foodorderplatform.repository.AddressRepository;
import com.example.foodorderplatform.repository.BusinessInfoRepository;
import com.example.foodorderplatform.repository.RegionRepository;
import com.example.foodorderplatform.repository.StoreRepository;
import com.example.foodorderplatform.repository.UserRepository;
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


    public ResponseEntity<String> createStore(StoreRequestDto storeRequestDto) {
        //todo 임시, 추후 시큐리티 도입 후 userDetails를 통해 가져오도록 변경
        User user = userRepository.findByUserName("yshong1998").get();
        // 지역 생성 or 조회
        Region region = regionRepository.findByRegionName(storeRequestDto.getRegionName()).orElse(null);
        if (region == null){
            region = regionRepository.save(new Region(storeRequestDto.getRegionName()));
        }

        // 상세 주소 생성
        Address address = addressRepository.save(new Address(storeRequestDto.getAddressName()));

        // 사업자 생성
        BusinessInfo businessInfo = businessInfoRepository.save(new BusinessInfo(storeRequestDto.getBusinessInfoRequestDto()));

        // 가게 생성
        storeRepository.save(new Store(user, region, address, storeRequestDto, businessInfo));
        return new ResponseEntity<>(CREATE_STORE_REQUEST_SUCCESS.getMessage(), HttpStatus.OK);
    }
}