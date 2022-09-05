package com.ecommerce.app.debodelivery.model;

import com.ecommerce.app.debodelivery.eenum.AddressType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressRequest {
    private String userPhoneNumber;
    private String fullName;
    private String phoneNumber;
    private Integer pinCode;
    private String state;
    private String city;
    private String houseNo;
    private String roadName;
    private AddressType addressType;
}