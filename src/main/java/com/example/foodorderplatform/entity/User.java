package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import com.example.foodorderplatform.dto.SignupRequestDto;
import com.example.foodorderplatform.dto.UserInfoRequestDto;
import com.example.foodorderplatform.enumclass.UserRoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
* check
* */

@Getter
@Entity
@NoArgsConstructor
@Table(name = "p_user")
public class User extends Timestamped {
	/*
	* User만 Id 전략이 IDENTITY
	* */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String userName;
	@Column(nullable = false)
	private String userNickName;
	@Column(nullable = false)
	private String userPw;
	@Column(nullable = false)
	private LocalDate userBirth;
	@Column(nullable = false)
	private String userTel;
	@Column(nullable = false)
	private String userEmail;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRoleEnum role;

	// 지역과의 연관관계
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	private Region region;

	// 주소와의 연관관계
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	public User(SignupRequestDto requestDto){
		this.userName = requestDto.getUsername();
		this.userPw = requestDto.getUserPw();
		this.userNickName = requestDto.getUserNickname();
		this.userEmail = requestDto.getUserEmail();
		this.userBirth = requestDto.getUserBirth();
		this.userTel = requestDto.getUserTel();
		this.role = requestDto.getRole();

	}

	public void updateUser(UserInfoRequestDto requestDto){
		this.userName = requestDto.getUsername();
		this.userPw = requestDto.getUserPw();
		this.userNickName = requestDto.getUserNickname();
		this.userEmail = requestDto.getUserEmail();
		this.userBirth = requestDto.getUserBirth();
		this.userTel = requestDto.getUserTel();
	}

	public void setRegion(Region region){
		this.region = region;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public void setRole(UserRoleEnum role){
		this.role = role;
	}

	public void setUserPw(String userPw){
		this.userPw = userPw;
	}

	@Override
	public boolean equals(Object o) {
		User user = (User) o;
		return this.id.equals(user.getId());
	}
}
