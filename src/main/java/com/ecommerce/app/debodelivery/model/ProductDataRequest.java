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
    private String productActualPrice;
    private String productSellingPrice;
    private String productDescription;
    private String rating;
    private String stock;
    private String categoryName;
}
