package com.example.foodorderplatform.dto;

import com.example.foodorderplatform.entity.Store;
import java.time.LocalTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreInfoResponseDto {

    private UUID id;                    // 가게 아이디
    private String storeName;			// 가게 이름
    private Boolean storeState;			// 운영상태
    private String storeRestDay;		// 휴무일
    private String storeImg;			// 가게 이미지
    private String storeDescription;	// 가게 소개
    private LocalTime storeOpenHour;	// 가게 오픈 시간
    private LocalTime storeCloseHour;	// 가게 마감 시간
    private String ingredientOrigin;	// 원산지

    public StoreInfoResponseDto(Store store){
        this.id = store.getId();
        this.storeName = store.getStoreName();
        this.storeState = store.getStoreState();
        this.storeDescription = store.getStoreDescription();
        this.storeOpenHour = store.getStoreOpenHour();
        this.storeCloseHour = store.getStoreCloseHour();
        this.ingredientOrigin = store.getIngredientOrigin();
    }
}
