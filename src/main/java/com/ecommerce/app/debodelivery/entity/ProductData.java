package com.ecommerce.app.debodelivery.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ProductData")
public class ProductData {
    @Id
    @Column(name = "productId")
    private String productId;
    @Column(name = "productName")
    private String productName;
    @Column(name = "productActualPrice")
    private String productActualPrice;
    @Column(name = "discountSellingPrice")
    private String discountSellingPrice;
    @Column(name = "productSellingPrice")
    private String productSellingPrice;
    @Column(name = "addedOn")
    private String addedOn;
    @Column(name = "productDescription")
    private String productDescription;
    @Column(name = "rating")
    private String rating;
    @Column(name = "stock")
    private String stock;
    @Column(name = "productImage")
    private String productImage;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="categoryId", referencedColumnName = "categoryId")
    private Category category;
}
