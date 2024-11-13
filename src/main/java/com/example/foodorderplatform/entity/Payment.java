package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import com.example.foodorderplatform.enumclass.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

/*
* check
* */
@Getter
@Entity
@Table(name = "p_payment")
public class Payment extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private String bank;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentStatusEnum paymentStatus;
	@Column(nullable = false)
	private Long paymentPrice;

	// 주문과의 연관 관계
	@OneToOne
	@JoinColumn(name = "order_id")
	private Order order;

	// 사용자와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}

