package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.dto.BusinessInfoDto;
import jakarta.persistence.*;
import lombok.Getter;
import java.util.UUID;
import lombok.NoArgsConstructor;

/*
* check
* */

@Getter
@Entity
@Table(name = "p_business_info")
@NoArgsConstructor
public class BusinessInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private String businessRegistrationNumber;	// 사업자 등록 번호
	@Column(nullable = false)
	private String businessName;				// 상호명
	@Column(nullable = false)
	private String ownerName;					// 대표자명

	// 가게와의 연관 관계
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	public BusinessInfo(BusinessInfoDto requestDto){
		this.businessRegistrationNumber = requestDto.getBusinessRegistrationNumber();
		this.businessName = requestDto.getBusinessName();
		this.ownerName = requestDto.getOwnerName();
	}
}
