package com.example.foodorderplatform.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

/*
* check
* */

@Getter
@Entity
@Table(name = "p_menu_tag")
public class MenuTag {
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID tagNo;
	@Column(nullable = false)
	private String tag;

	// 가게와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "store_no")
	private Store store;
}
