package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "p_cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID cartNo;

	// 가게와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "store_no")
	private Store store;

	// 사용자와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "user_no")
	private User user;
}
