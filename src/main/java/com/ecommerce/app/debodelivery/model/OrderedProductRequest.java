package com.ecommerce.app.debodelivery.model;

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
}
