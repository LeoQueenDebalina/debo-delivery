package com.ecommerce.app.debodelivery.model;

import com.ecommerce.app.debodelivery.util.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedProductRequest {
    private String userMobileNumber;
    private String productId;
    private String addressId;
    private PaymentType paymentType;
}
