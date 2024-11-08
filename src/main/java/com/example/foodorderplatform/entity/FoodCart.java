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
@Table(name = "p_food_cart")
public class FoodCart {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID cartNo;
	@Column(nullable = false)
	private Integer foodCnt;

	@ManyToOne
	@JoinColumn(name = "cart_no")
	private Cart cart;

	@ManyToOne
	@JoinColumn(name = "food_no")
	private Food food;
}