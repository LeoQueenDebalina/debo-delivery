package com.ecommerce.app.debodelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewCartResponse {
    private String productId;
    private String productName;
    private Integer productSellingPrice;
    private String cartId;
}
