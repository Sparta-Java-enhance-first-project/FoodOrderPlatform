package com.example.foodorderplatform.dto;


import com.example.foodorderplatform.enumclass.UserRoleEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    private String username;
    private String userPw;
    private String userNickname;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate userBirth;
    private String userTel;
    private String userEmail;
    private String regionName;
    private String addressName;
    private UserRoleEnum role;
    private String adminToken = "";
}
