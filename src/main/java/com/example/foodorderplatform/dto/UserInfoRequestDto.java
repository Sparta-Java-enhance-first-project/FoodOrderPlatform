package com.example.foodorderplatform.dto;

import com.example.foodorderplatform.enumclass.UserRoleEnum;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoRequestDto {
    private String username;
    private String userNickname;
    private String userPw;
    private LocalDate userBirth;
    private String userTel;
    private String userEmail;
    private UserRoleEnum role;
    private String regionName;
    private String addressName;
}
