package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import com.example.foodorderplatform.enumclass.OrderStatusEnum;
import com.example.foodorderplatform.enumclass.OrderTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

/*
* check
* */
@Getter
@Entity
@Table(name = "p_order")
public class Order extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID orderNo;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatusEnum orderStatus;	// 주문상태
	@Column(nullable = false)
	private Long orderPrice;				// 통합가격
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderTypeEnum orderType;		// 주문 유형
	@Column
	private String orderRequest;			// 주문 요청사항

	// 유저와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "user_no")
	private User user;

	// 가게와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "store_no")
	private Store store;

	// 결제와의 연관 관계
	@OneToOne(mappedBy = "order")
	private Payment payment;
}
