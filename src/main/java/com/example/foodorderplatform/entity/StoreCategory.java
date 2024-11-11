package com.example.foodorderplatform.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

/*
* check
* */

@Getter
@Entity
@Table(name = "p_store_category")
public class StoreCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID categoryId;

	@Column(nullable = false)
	private String categoryNm;	// 카테고리 이름
}
