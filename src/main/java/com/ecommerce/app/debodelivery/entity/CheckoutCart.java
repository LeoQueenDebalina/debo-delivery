package com.ecommerce.app.debodelivery.entity;

import com.ecommerce.app.debodelivery.eenum.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CheckoutCart")
public class CheckoutCart {
    @Id
    @Column(name = "checkoutId")
    private String checkoutId;
    @Column(name = "paymentType")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Column(name = "deliveryAddress")
    private String deliveryAddress;
    @Column(name = "userId")
    private String userId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="productId", referencedColumnName = "productId")
    private ProductData productData;
    @Column(name = "quantity")
    private String quantity;
    @Column(name = "orderDate")
    private String orderDate;
    @Column(name = "deliveryDate")
    private String deliveryDate;
}
