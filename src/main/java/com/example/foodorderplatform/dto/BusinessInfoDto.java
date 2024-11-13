package com.example.foodorderplatform.dto;

import com.example.foodorderplatform.entity.BusinessInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BusinessInfoDto {

    private String businessRegistrationNumber;
    private String businessName;
    private String ownerName;

    public BusinessInfoDto(BusinessInfo businessInfo){
        this.businessRegistrationNumber = businessInfo.getBusinessRegistrationNumber();
        this.businessName = businessInfo.getBusinessName();
        this.ownerName = businessInfo.getOwnerName();
    }
}
