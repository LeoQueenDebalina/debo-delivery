package com.ecommerce.app.debodelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ProductData")
public class ProductDataRequest {
    private String productName;
    private Integer productActualPrice;
    private Integer discountSellingPrice;
    private String productDescription;
    private Float rating;
    private Integer stock;
    private String categoryName;
    private String companyName;
    private String productImageUrl;
}
