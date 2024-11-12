package com.example.foodorderplatform.service;

import static com.example.foodorderplatform.message.SuccessMessage.*;

import com.example.foodorderplatform.dto.UserInfoRequestDto;
import com.example.foodorderplatform.entity.Address;
import com.example.foodorderplatform.repository.AddressRepository;
import com.example.foodorderplatform.dto.SignupRequestDto;
import com.example.foodorderplatform.dto.UserInfoResponseDto;
import com.example.foodorderplatform.entity.Region;
import com.example.foodorderplatform.entity.User;
import com.example.foodorderplatform.repository.RegionRepository;
import com.example.foodorderplatform.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "userService")
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RegionRepository regionRepository;
    private final AddressRepository addressRepository;

    public ResponseEntity<String> signup(SignupRequestDto requestDto) {
        User user = new User(requestDto);
        Optional<Region> region = regionRepository.findByRegionName(requestDto.getRegionName());
        if (region.isPresent()){
            user.setRegion(region.get());
        } else {
            Region newRegion = new Region(requestDto.getRegionName());
            regionRepository.save(newRegion);
            user.setRegion(newRegion);
        }
        Address address = addressRepository.save(new Address(requestDto.getAddressName()));
        user.setAddress(address);
        userRepository.save(user);
        return new ResponseEntity<>(SIGNUP_SUCCESS.getMessage(), HttpStatus.OK);
    }

    public ResponseEntity<UserInfoResponseDto> getUserInfo() {
        User user = userRepository.findByUserName("yshong1998").orElseThrow(
                () -> new NullPointerException("존재하지 않는 회원입니다.")
        );
        return new ResponseEntity<>(new UserInfoResponseDto(user), HttpStatus.OK);
    }

    public ResponseEntity<UserInfoResponseDto> updateUserInfo(UserInfoRequestDto requestDto) {
        User user = userRepository.findByUserName("yshong1998").orElseThrow(
                () -> new NullPointerException("존재하지 않는 회원입니다.")
        );
        Optional<Region> region = regionRepository.findByRegionName(requestDto.getRegionName());
        if (region.isPresent()){
            user.setRegion(region.get());
        } else {
            Region newRegion = new Region(requestDto.getRegionName());
            regionRepository.save(newRegion);
            user.setRegion(newRegion);
        }
        user.getAddress().setAddressName(requestDto.getAddressName());
        user.updateUser(requestDto);
        return new ResponseEntity<>(new UserInfoResponseDto(user), HttpStatus.OK);
    }
}
