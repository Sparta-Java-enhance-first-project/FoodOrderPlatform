package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import com.example.foodorderplatform.enumclass.UserRoleEnum;
import jakarta.persistence.*;
import lombok.*;

/*
* check
* */

@Getter
@Entity
@Table(name = "p_user")
public class User extends Timestamped {
	/*
	* User만 Id 전략이 IDENTITY
	* */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userNo;
	@Column(nullable = false, unique = true)
	private String userId;
	@Column(nullable = false)
	private String userPw;
	@Column(nullable = false)
	private String userNm;
	@Column(nullable = false)
	private String userBirth;
	@Column(nullable = false)
	private String userTel;
	@Column(nullable = false)
	private String userEmail;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRoleEnum role;

	// 지역과의 연관관계
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "region_no")
	private Region region;

}
