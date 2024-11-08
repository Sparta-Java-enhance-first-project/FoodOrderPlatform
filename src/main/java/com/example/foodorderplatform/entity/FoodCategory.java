package com.example.foodorderplatform.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

/*
* check
* */
@Getter
@Entity
@Table(name = "p_food_category")
public class FoodCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID categoryNo;
	@Column(nullable = false)
	private String categoryNm;

	// 음식과의 연관 관계
	@ManyToOne
	@JoinColumn(name = "food_no")
	private Food food;
}