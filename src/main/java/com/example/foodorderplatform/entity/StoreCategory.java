package com.example.foodorderplatform.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "p_store_category")
public class StoreCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long category_no;

	@Column(nullable = false)
	private String category_nm;

	@ManyToOne
	@JoinColumn(name = "store_no")
	private Store store;
}
