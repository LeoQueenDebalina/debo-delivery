package com.ecommerce.app.debodelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalPriceInCartResponse {
    private Integer productActualPrice;
    private Integer discountSellingPrice;
    private Integer productSellingPrice;
}
