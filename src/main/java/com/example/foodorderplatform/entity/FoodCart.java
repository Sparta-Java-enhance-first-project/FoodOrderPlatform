package com.example.foodorderplatform.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

/*
* check
* */
@Getter
@Entity
@Table(name = "p_food_cart")
public class FoodCart {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID foodCartId;
	@Column(nullable = false)
	private Integer foodCnt;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@ManyToOne
	@JoinColumn(name = "food_id")
	private Food food;
}
