package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import java.sql.Time;
import java.util.UUID;

/*
* check
* */

@Getter
@Entity
@Table(name = "p_store")
public class Store extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID storeId;
	@Column(nullable = false)
	private String storeNm;				// 가게 이름
	@Column(nullable = false)
	private Boolean storeState;			// 운영상태
	@Column
	private String storeRestDay;		// 휴무일
	@Column
	private String storeImg;			// 가게 이미지
	@Column(columnDefinition = "text")
	private String storeDescription;	// 가게 소개
	@Column(nullable = false)
	private Time storeOpenHour;			// 가게 오픈 시간
	@Column(nullable = false)
	private Time storeCloseHour;		// 가게 마감 시간
	@Column(columnDefinition = "text")
	private String ingredientOrigin;	// 원산지

	// 유저와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "user_no")
	private User user;

	// 가게 카테고리와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "store_category_no")
	private StoreCategory storeCategory;

	// 지역과의 연관 관계
	@ManyToOne
	@JoinColumn(name = "region_no")
	private Region region;

	// 주소와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "address_no")
	private Address address;

	// 사업자 등록증 정보와의 관계
	@OneToOne(mappedBy = "store")
	private BusinessInfo businessInfo;

}
