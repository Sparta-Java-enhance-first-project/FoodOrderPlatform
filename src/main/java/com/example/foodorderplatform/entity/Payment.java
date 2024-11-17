package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import com.example.foodorderplatform.enumclass.BankEnum;
import com.example.foodorderplatform.enumclass.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

/*
* check
* */
@Getter
@Entity
@Table(name = "p_payment")
@NoArgsConstructor
public class Payment extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private BankEnum bank;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentStatusEnum paymentStatus;
	@Column(nullable = false)
	private Long paymentPrice;

	// 주문과의 연관 관계
	@OneToOne(mappedBy = "payment")
	private Order order;

	// 사용자와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Payment(User user, BankEnum bank, Long paymentPrice) {
		this.user = user;
		this.bank = bank;
		this.paymentStatus = PaymentStatusEnum.PAYMENT_REQUEST;
		this.paymentPrice = paymentPrice;
	}
}

