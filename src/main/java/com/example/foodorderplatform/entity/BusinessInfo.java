package com.example.foodorderplatform.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "p_business_info")
public class BusinessInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long business_info_no;
	@Column(nullable = false)
	private String business_registration_number;
	@Column(nullable = false)
	private String business_name;
	@Column(nullable = false)
	private String owner_name;

	@OneToOne
	@JoinColumn(name = "store_no")
	private Store store;
}
