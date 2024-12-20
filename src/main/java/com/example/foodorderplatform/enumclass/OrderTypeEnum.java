package com.example.foodorderplatform.enumclass;


public enum OrderTypeEnum {
	TAKE_OUT(OrderTypeEnum.Type.TAKE_OUT),	// 포장주문
	ONLINE(OrderTypeEnum.Type.ONLINE);			// 배달주문



	private final String type;

	OrderTypeEnum(String type) {
		this.type = type;
	}

	public String getType(){
		return this.type;
	}

	public static class Type{
		public static final String TAKE_OUT = "포장주문";
		public static final String ONLINE = "배달주문";
	}
}
