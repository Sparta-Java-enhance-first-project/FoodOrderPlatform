package com.example.foodorderplatform.enumclass;

public enum ReceiveTypeEnum {
    FOR_HERE(ReceiveType.FOR_HERE),
    TAKE_OUT(ReceiveType.TAKE_OUT),
    DELEVERY(ReceiveType.DELEVERY),
    ;

    private final String receiveType;

    ReceiveTypeEnum(String receiveType) {
        this.receiveType = receiveType;
    }

    private String getReceiveType(){
        return receiveType;
    }

    public static class ReceiveType{
        public static final String FOR_HERE = "매장";
        public static final String TAKE_OUT = "포장";
        public static final String DELEVERY = "배달";
    }
}
