package com.example.foodorderplatform.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "p_menu_tag")
public class MenuTag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tag_no;
	@Column(nullable = false)
	private String tag;

	@ManyToOne
	@JoinColumn(name = "store_no")
	private Store store;
}
