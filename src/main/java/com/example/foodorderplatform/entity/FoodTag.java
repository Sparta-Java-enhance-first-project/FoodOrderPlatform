package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

import java.util.UUID;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "p_food_tag")
@NoArgsConstructor
public class FoodTag extends Timestamped {
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private String foodTagName;

	// 가게와의 연관 관계
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	@OneToMany(mappedBy = "id")
	private List<Food> foodList = new ArrayList<>();

	public FoodTag(Store store, String foodTagName){
		this.store = store;
		this.foodTagName = foodTagName;
	}

	public void updateFoodTagName(String foodTagName) {
		this.foodTagName = foodTagName;
	}
}
