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
@Table(name = "p_review")
public class Review extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private Integer rating;
	@Column
	private String reviewImage;
	@Column(columnDefinition = "text", nullable = false)
	private String reviewContent;

	// 음식과의 연관 관계
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "food_id")
	private Food food;

	// 사용자와의 연관 관계
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	// 가게와의 연관 관계
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;
}
