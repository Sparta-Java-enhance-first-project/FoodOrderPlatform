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
	private UUID reviewNo;
	@Column(nullable = false)
	private Integer rating;
	@Column
	private String reviewImage;
	@Column(columnDefinition = "text", nullable = false)
	private String reviewContent;

	// 음식과의 연관 관계
	@ManyToOne
	@JoinColumn(name = "food_no")
	private Food food;

	// 사용자와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "user_no")
	private User user;

	// 가게와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "store_no")
	private Store store;
}
