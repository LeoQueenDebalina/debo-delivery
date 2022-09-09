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
    @Pattern(regexp = "[\\d]")
    @NotBlank
    private String productName;
    @Pattern(regexp = "[\\d]+" ,message = "Should contain digits only")
    @NotBlank
    private Integer productActualPrice;
    @Pattern(regexp = "[\\d]+" ,message = "Should contain digits only")
    @NotBlank
    private Integer discountSellingPrice;

    @NotBlank@Pattern(regexp = "/^[a-z .,]+$/i", message = "should contain character, dot, comma and space only")
    private String productDescription;
    @Pattern(regexp = "[\\d.]+" ,message = "Should contain digits only")
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
