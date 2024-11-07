package com.example.foodorderplatform.entity;

public enum UserRoleEnum {
	USER(Authority.USER),	// 사용자
	PRESIDENT(Authority.OWNER),	// 가게 사장님
	ADMIN(Authority.ADMIN),	// 관리자
	;

	private final String authority;

	UserRoleEnum(String authority) {
		this.authority = authority;
	}

	public String getAuthority(){
		return this.authority;
	}

	public static class Authority{
		public static final String USER = "ROLE_USER";
		public static final String OWNER = "ROLE_OWNER";
		public static final String ADMIN = "ROLE_ADMIN";
	}
}
