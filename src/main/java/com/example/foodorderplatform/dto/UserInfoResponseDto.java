package com.example.foodorderplatform.dto;

import com.example.foodorderplatform.entity.User;
import com.example.foodorderplatform.enumclass.UserRoleEnum;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoResponseDto {

    private String userName;
    private String userNickname;
    private String userPw;
    private LocalDate userBirth;
    private String userTel;
    private String userEmail;
    private UserRoleEnum role;
    private String regionName;
    private String addressName;

    public UserInfoResponseDto(User user){
        this.userName = user.getUserName();
        this.userNickname = user.getUserNickName();
        this.userPw = user.getUserPw();
        this.userBirth = user.getUserBirth();
        this.userTel = user.getUserTel();
        this.userEmail = user.getUserEmail();
        this.role = user.getRole();
        this.regionName = user.getRegion().getRegionName();
        this.addressName = user.getAddress().getAddressName();
    }
}
