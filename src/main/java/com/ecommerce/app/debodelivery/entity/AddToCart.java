package com.ecommerce.app.debodelivery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AddToCart")
public class AddToCart {
    @Id
    @Column(name = "cartId")
    private String cartId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="productId", referencedColumnName = "productId")
    private ProductData productData;
    @Column(name = "quantity")
    private String quantity;
    @Column(name = "price")
    private String price;
    @Column(name = "userId")
    private String userId;
    @Column(name = "addedDate")
    private String addedDate;
    @Column(name = "productName")
    private String productName;
}
