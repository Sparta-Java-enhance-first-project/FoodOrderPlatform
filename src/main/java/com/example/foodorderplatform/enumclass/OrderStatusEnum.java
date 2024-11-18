package com.example.foodorderplatform.enumclass;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
	PAYMENT_COMPLETED(OrderStatusEnum.Status.PAYMENT_COMPLETED),
	RECEIPT(OrderStatusEnum.Status.RECEIPT),	// 주문접수
	COOK(OrderStatusEnum.Status.COOK),			// 조리중
	COOCK_COMPLETED(OrderStatusEnum.Status.COOK),			// 조리완료
	ORDER_CANCEL(OrderStatusEnum.Status.COOK),			// 주문취소
	ORDER_FAIL(OrderStatusEnum.Status.ORDER_FAIL),
	DELIVERY(OrderStatusEnum.Status.DELIVERY),	// 배달중
	SUCCESS(OrderStatusEnum.Status.SUCCESS),	// 배달완료
	CANCEL(OrderStatusEnum.Status.CANCEL),		// 배달취소
	;

	private final String status;

	OrderStatusEnum(String status) {
		this.status = status;
	}

	public String getStatus(){
		return this.status;
	}

	public static class Status{
		public static final String PAYMENT_COMPLETED = "결제완료";
		public static final String RECEIPT = "주문접수";
		public static final String COOK = "조리중";
		public static final String COOK_COMPLETED = "조리완료";
		public static final String ORDER_CANCEL = "주문취소";
		public static final String ORDER_FAIL = "주문실패";
		public static final String DELIVERY = "배달중";
		public static final String SUCCESS = "배달완료";
		public static final String CANCEL = "배달취소";
		// 포장수령완료
		// 매장서빙완료
	}
}
