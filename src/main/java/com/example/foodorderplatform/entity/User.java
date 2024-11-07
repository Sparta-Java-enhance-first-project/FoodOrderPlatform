package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@Table(name = "p_user")
public class User extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_no;		// user_no
	@Column(nullable = false)
	private String user_id;		// user id
	@Column(nullable = false)
	private String user_pw;		// user password
	@Column(nullable = false)
	private String user_nm;		// user name
	@Column(nullable = false)
	private String user_birth;	// user birthday
	@Column(nullable = false)
	private String user_tel;	// user telephone
	@Column(nullable = false)
	private String user_email;	// user email
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRoleEnum role;	// user role

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "region_no")
	private Region region;

}
