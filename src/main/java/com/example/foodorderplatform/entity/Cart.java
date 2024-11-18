package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "p_cart")
@NoArgsConstructor
public class Cart extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	// 가게와의 연관 관계
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;


	// 사용자와의 연관 관계
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "cart")
	private List<FoodCart> foodCarts;

	public Cart(User user, Store store){
		this.user = user;
		this.store = store;
	}
}
