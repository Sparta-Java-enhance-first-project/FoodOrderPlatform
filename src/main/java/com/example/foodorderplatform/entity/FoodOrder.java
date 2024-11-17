package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import com.example.foodorderplatform.dto.FoodCartRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;
/*
* check
* */
@Getter
@Entity
@Table(name = "p_food_order")
@NoArgsConstructor
public class FoodOrder extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private Integer foodCnt;	// 음식개수

	// 주문과의 연관 관계
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	// 음식과의 연관 관계
	@ManyToOne
	@JoinColumn(name = "food_id")
	private Food food;

	public FoodOrder(Order order, Food food, int foodCnt) {
		this.order = order;
		this.food = food;
		this.foodCnt = foodCnt;
	}
}
