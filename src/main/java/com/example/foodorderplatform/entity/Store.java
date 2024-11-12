package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import com.example.foodorderplatform.dto.StoreRequestDto;
import com.example.foodorderplatform.enumclass.StoreConfirmStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
* check
* */

@Getter
@Entity
@Table(name = "p_store")
@NoArgsConstructor
public class Store extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID storeId;
	@Column(nullable = false)
	private String storeName;			// 가게 이름
	@Column(nullable = false)
	private Boolean storeState;			// 운영상태
	@Column
	private String storeRestDay;		// 휴무일
	@Column
	private String storeImg;			// 가게 이미지
	@Column(columnDefinition = "text")
	private String storeDescription;	// 가게 소개
	@Column(nullable = false)
	private LocalTime storeOpenHour;			// 가게 오픈 시간
	@Column(nullable = false)
	private LocalTime storeCloseHour;		// 가게 마감 시간
	@Column(columnDefinition = "text")
	private String ingredientOrigin;	// 원산지
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StoreConfirmStatus confirmStatus;

	// 유저와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	// 가게 카테고리와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "store_category_no")
	private StoreCategory storeCategory;

	// 지역과의 연관 관계
	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;

	// 주소와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	// 사업자 등록증 정보와의 관계
	@OneToOne
	private BusinessInfo businessInfo;

	public Store(User user, Region region, Address address, StoreRequestDto requestDto, BusinessInfo businessInfo){
		this.user = user;
		this.region = region;
		this.address = address;
		this.storeName = requestDto.getStoreName();
		this.businessInfo = businessInfo;
		this.ingredientOrigin = requestDto.getIngredientOrigin();
		this.storeCloseHour = requestDto.getStoreCloseHour();
		this.storeOpenHour = requestDto.getStoreOpenHour();
		this.storeDescription = requestDto.getStoreDescription();
		this.storeRestDay = requestDto.getStoreRestDay();
		this.storeState = false;
		this.confirmStatus = StoreConfirmStatus.REQUIRED;
	}
}
