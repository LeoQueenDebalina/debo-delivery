package com.ecommerce.app.debodelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDataResponse {
    private String productId;
    private String productName;
    private Integer productActualPrice;
    private Integer discountSellingPrice;
    private Integer productSellingPrice;
    private String productDescription;
    private Float rating;
    private Integer stock;
    private String categoryName;
    private String companyName;
    private String imageId;
}
