package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import com.example.foodorderplatform.enumclass.BankEnum;
import com.example.foodorderplatform.enumclass.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	@Setter
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentStatusEnum paymentStatus;
	@Column(nullable = false)
	private Long paymentPrice;

	// 주문과의 연관 관계
	@Setter
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "payment")
	private Order order;

	// 사용자와의 연관 관계
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public Payment(User user, BankEnum bank, Long paymentPrice) {
		this.id = UUID.randomUUID();
		this.user = user;
		this.bank = bank;
		this.paymentStatus = PaymentStatusEnum.PAYMENT_REQUEST;
		this.paymentPrice = paymentPrice;
	}

	public void addOrder(Order order) {
		this.order = order;
		order.setPayment(this); // 외래 키(연관 관계) 설정
	}
}

