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
	private UUID id;
	@Column(nullable = false)
	private String categoryName;

	// 음식과의 연관 관계
	@ManyToOne
	@JoinColumn(name = "food_id")
	private Food food;
}
