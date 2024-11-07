package com.example.foodorderplatform.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "p_cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cart_no;

	@ManyToOne
	@JoinColumn(name = "store_no")
	private Store store;

	@ManyToOne
	@JoinColumn(name = "user_no")
	private User user;
}
