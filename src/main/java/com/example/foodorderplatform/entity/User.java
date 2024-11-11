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
	private Long userId;
	@Column(nullable = false, unique = true)
	private String userName;
	@Column(nullable = false)
	private String userNickName;
	@Column(nullable = false)
	private String userPw;
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
	@ManyToOne
	@JoinColumn(name = "region_no")
	private Region region;

	// 주소와의 연관관계
	@ManyToOne
	@JoinColumn(name = "address_no")
	private Address address;
}
