package com.ecommerce.app.debodelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ProductData")
public class ProductDataRequest {
    @NotBlank@Pattern(regexp = "[a-z\\d .,]+", message = "should contain character, dot, number, comma and space only")
    @NotBlank
    private String productName;
    @NotNull
    private Integer productActualPrice;
    @NotNull
    private Integer discountSellingPrice;
    @NotBlank
    private String productDescription;
    @NotNull
    private Float rating;
    @NotNull
    private Integer stock;
    @NotBlank
    private String categoryName;
    @NotBlank
    private String companyName;
    @Pattern(regexp = "^(.+)\\/([^\\/]+)$" )
    @NotBlank
    private String productImageUrl;
}
