package com.example.foodorderplatform.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;
/*
* check
* */
@Getter
@Entity
@Table(name = "p_food_order")
public class FoodOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID foodOrderNo;
	@Column(nullable = false)
	private Integer foodCnt;	// 음식개수

	// 주문과의 연관 관계
	@ManyToOne
	@JoinColumn(name = "order_no")
	private Order oder;

	// 음식과의 연관 관계
	@ManyToOne
	@JoinColumn(name = "order_no")
	private Food food;
}
