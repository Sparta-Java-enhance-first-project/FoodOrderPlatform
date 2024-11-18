package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

/*
* check
* */
@Getter
@Entity
@Table(name = "p_food_cart")
@NoArgsConstructor
public class FoodCart extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private Integer foodCnt;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@ManyToOne
//	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "food_id")
	private Food food;

	public void setFoodCnt(Integer foodCnt) {
		this.foodCnt = foodCnt;
	}

	public FoodCart(Cart cart, Food food) {
		this.cart = cart;
		this.food = food;
		this.foodCnt = 0;
	}

}
