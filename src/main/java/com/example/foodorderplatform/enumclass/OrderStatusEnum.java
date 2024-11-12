package com.example.foodorderplatform.enumclass;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
	RECEIPT(OrderStatusEnum.Status.RECEIPT),	// 주문접수
	COOK(OrderStatusEnum.Status.COOK),			// 조리중
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
		public static final String RECEIPT = "주문접수";
		public static final String COOK = "조리중";
		public static final String DELIVERY = "배달중";
		public static final String SUCCESS = "배달완료";
		public static final String CANCEL = "배달취소";
	}
}
