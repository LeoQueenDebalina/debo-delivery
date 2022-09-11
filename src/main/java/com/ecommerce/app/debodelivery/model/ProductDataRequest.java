package com.ecommerce.app.debodelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ProductData")
public class ProductDataRequest {
    @NotBlank@Pattern(regexp = "[a-z\\d .,]+", message = "should contain character, dot, number, comma and space only")
    @NotBlank
    private String productName;
    @NotBlank
    private Integer productActualPrice;
    @NotBlank
    private Integer discountSellingPrice;
    @NotBlank
    private String productDescription;
    @NotBlank
    private Float rating;
    @Pattern(regexp = "[\\d]+")
    @NotBlank
    private Integer stock;
    @Pattern(regexp = "[\\d]" )
    @NotBlank
    private String categoryName;
    @Pattern(regexp = "[\\d]" )
    @NotBlank
    private String companyName;
    @Pattern(regexp = "^(.+)\\/([^\\/]+)$" )
    @NotBlank
    private String productImageUrl;
}
