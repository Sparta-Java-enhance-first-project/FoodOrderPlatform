package com.example.foodorderplatform.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequestDto {
    private UUID paymentId;
    private String userNickName;
    private LocalDate userBirth;
    private String userTel;
    private String userEmail;
    private Long paymentPrice;

    public PaymentRequestDto(UUID paymentId, String userNickName, LocalDate userBirth, String userTel, String userEmail, Long paymentPrice) {
        this.paymentId = paymentId;
        this.userNickName = userNickName;
        this.userBirth = userBirth;
        this.userTel = userTel;
        this.userEmail = userEmail;
        this.paymentPrice = paymentPrice;
    }
}
