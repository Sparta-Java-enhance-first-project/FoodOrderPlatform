package com.example.foodorderplatform.entity;

import jakarta.persistence.*;
import lombok.Getter;
import java.util.UUID;

/*
* check
* */

@Getter
@Entity
@Table(name = "p_business_info")
public class BusinessInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID businessInfoId;
	@Column(nullable = false)
	private String businessRegistrationNumber;	// 사업자 등록 번호
	@Column(nullable = false)
	private String businessName;				// 상호명
	@Column(nullable = false)
	private String ownerName;					// 대표자명

	// 가게와의 연관 관계
	@OneToOne
	@JoinColumn(name = "store_id")
	private Store store;
}
