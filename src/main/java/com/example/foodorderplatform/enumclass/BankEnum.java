package com.example.foodorderplatform.enumclass;

import lombok.Getter;

@Getter
public enum BankEnum {
    HANA(BankName.HANA),
    SHINHAN(BankName.SHINHAN),
    KB(BankName.KB),
    ;

    private final String bankName;

    BankEnum(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public static class BankName{
        public static final String HANA = "하나";
        public static final String SHINHAN = "신한";
        public static final String KB = "KB";
    }

}
