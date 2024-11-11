package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;
/*
* check
* */
@Getter
@Entity
@Table(name = "p_food")
public class Food extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID foodId;
	@Column(nullable = false)
	private String foodName;		// 음식 이름
	@Column(nullable = false)
	private String foodDesc;	// 음식 설명
	@Column
	private String foodImage;	// 음식 이미지
	@Column(nullable = false)
	private String foodPrice;	// 음식 가격
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
	@JoinColumn(name = "menu_tag_id")
	private MenuTag menuTag;

}
