package com.example.foodorderplatform.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreRequestDto {

    private String storeName;			// 가게 이름
    private Boolean storeState = false;			// 운영상태
    private String storeRestDay;		// 휴무일
    private String storeImg;			// 가게 이미지
    private String storeDescription;	// 가게 소개
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime storeOpenHour;			// 가게 오픈 시간
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime storeCloseHour;		// 가게 마감 시간
    private String ingredientOrigin;	// 원산지
    private String regionName;          // 지역 이름
    private String addressName;         // 상세 주소
    private BusinessInfoRequestDto businessInfoRequestDto;

}
