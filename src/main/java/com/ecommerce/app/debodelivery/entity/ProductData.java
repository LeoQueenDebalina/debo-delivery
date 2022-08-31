package com.ecommerce.app.debodelivery.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
    private Integer productActualPrice;
    @Column(name = "discountSellingPrice")
    private Integer discountSellingPrice;
    @Column(name = "productSellingPrice")
    private Integer productSellingPrice;
    @Column(name = "addedOn")
    @Temporal(TemporalType.DATE)
    private Date addedOn;
    @Column(name = "productDescription")
    private String productDescription;
    @Column(name = "rating")
    private Float rating;
    @Column(name = "stock")
    private Integer stock;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "imageId", referencedColumnName = "imageId")
    private ProductImage productImage;
    @Column(name = "companyName")
    private String companyName;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    private Category category;
}
