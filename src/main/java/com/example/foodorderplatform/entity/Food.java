package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import com.example.foodorderplatform.dto.FoodRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;
import lombok.NoArgsConstructor;

/*
* check
* */
@Getter
@Entity
@Table(name = "p_food")
@NoArgsConstructor
public class Food extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private String foodName;		// 음식 이름
	@Column(nullable = false)
	private String foodDesc;	// 음식 설명
	@Column
	private String foodImage;	// 음식 이미지
	@Column(nullable = false)
	private Long foodPrice;	// 음식 가격
	@Column(nullable = false)
	private Boolean foodState;	// 주문가능여부
	@Column
	private Boolean recommand;	// 추천

	// 가게와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	// 메뉴 태그와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "food_tag_id")
	private FoodTag foodTag;

	public Food(Store store, FoodTag foodTag, FoodRequestDto requestDto){
		this.store = store;
		this.foodTag = foodTag;
		this.foodName = requestDto.getFoodName();
		this.foodDesc = requestDto.getFoodDesc();
//		this.foodImage = requestDto.getFoodImage();
		this.foodPrice = requestDto.getFoodPrice();
		this.foodState = requestDto.getFoodState();
		this.recommand = requestDto.getRecommand();
	}

	public void updateFood (FoodTag foodTag, FoodRequestDto requestDto){
		this.foodTag = foodTag;
		this.foodName = requestDto.getFoodName();
		this.foodDesc = requestDto.getFoodDesc();
//		this.foodImage = requestDto.getFoodImage();
		this.foodPrice = requestDto.getFoodPrice();
		this.foodState = requestDto.getFoodState();
		this.recommand = requestDto.getRecommand();
	}
}
