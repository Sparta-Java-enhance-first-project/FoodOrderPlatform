package com.example.foodorderplatform.service;

import static com.example.foodorderplatform.message.SuccessMessage.*;

import com.example.foodorderplatform.dto.UserInfoRequestDto;
import com.example.foodorderplatform.entity.Address;
import com.example.foodorderplatform.enumclass.UserRoleEnum;
import com.example.foodorderplatform.repository.AddressRepository;
import com.example.foodorderplatform.dto.SignupRequestDto;
import com.example.foodorderplatform.dto.UserInfoResponseDto;
import com.example.foodorderplatform.entity.Region;
import com.example.foodorderplatform.entity.User;
import com.example.foodorderplatform.repository.RegionRepository;
import com.example.foodorderplatform.repository.UserRepository;
import java.util.Optional;

import com.example.foodorderplatform.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

    @Value(value = "${user.admin.key}")
    private String ADMIN_TOKEN;

    @Transactional
    public ResponseEntity<String> signup(SignupRequestDto requestDto) {
        String password = passwordEncoder.encode(requestDto.getUserPw());
        User user = new User(requestDto);
        user.setUserPw(password);
        if (requestDto.getRole() == UserRoleEnum.ADMIN) {

            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
             user.setRole(UserRoleEnum.ADMIN);
        }

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

    public ResponseEntity<UserInfoResponseDto> getUserInfo(UserDetailsImpl userDetails) {

        User user = userRepository.findByUserName(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("존재하지 않는 회원입니다.")
        );
        return new ResponseEntity<>(new UserInfoResponseDto(user), HttpStatus.OK);
    }

    public ResponseEntity<UserInfoResponseDto> updateUserInfo(UserInfoRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = userRepository.findByUserName("yeongho").orElseThrow(
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
