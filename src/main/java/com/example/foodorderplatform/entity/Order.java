package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import com.example.foodorderplatform.dto.OrderRequestDto;
import com.example.foodorderplatform.enumclass.OrderStatusEnum;
import com.example.foodorderplatform.enumclass.OrderTypeEnum;
import com.example.foodorderplatform.enumclass.ReceiveTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/*
* check
* */
@Getter
@Entity
@Table(name = "p_order")
@NoArgsConstructor
public class Order extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@Setter
	private OrderStatusEnum orderStatus;	// 주문상태
	@Column(nullable = false)
	private Long orderPrice;				// 통합가격
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderTypeEnum orderType;		// 주문 유형
	@Column
	private String orderRequest;			// 주문 요청사항
	@Column(nullable = false)
	private ReceiveTypeEnum receiveType;

	// 유저와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	// 가게와의 연관 관계
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	// 결제와의 연관 관계
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_id")
	private Payment payment;

	@OneToMany(mappedBy = "order")
	private List<FoodOrder> foodOrderList;

	public Order(Store store, User user, OrderStatusEnum orderStatus, OrderRequestDto orderRequest) {
//	public Order(OrderStatusEnum orderStatus, OrderRequestDto orderRequest) {
		this.store = store;
		this.user = user;
		this.orderStatus = orderStatus;
		this.orderPrice = orderRequest.getOrderPrice();
		this.orderType = orderRequest.getOrderType();
		this.receiveType = orderRequest.getReceiveType();
		this.orderRequest = orderRequest.getOrderRequest();

	}
	public void setOrderRequest(String orderRequest) {
		this.orderRequest = orderRequest;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
