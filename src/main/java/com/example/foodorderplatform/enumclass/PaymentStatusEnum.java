package com.example.foodorderplatform.enumclass;

import lombok.Getter;

@Getter
public enum PaymentStatusEnum {
	SUCCESS(PaymentStatusEnum.Status.SUCCESS),	// 결제승인
	FAIL(PaymentStatusEnum.Status.FAIL),		// 결제실패
	CANCEL(PaymentStatusEnum.Status.CANCEL);	// 결제취소(환불)



	private final String status;

	PaymentStatusEnum(String status) {
		this.status = status;
	}

	public String getStatus(){
		return this.status;
	}

	public static class Status{
		public static final String SUCCESS = "결제승인";
		public static final String FAIL = "결제실패";
		public static final String CANCEL = "결제취소";
	}
}
