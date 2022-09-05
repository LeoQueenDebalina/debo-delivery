package com.ecommerce.app.debodelivery.entity;

import com.ecommerce.app.debodelivery.eenum.OrderStatus;
import com.ecommerce.app.debodelivery.eenum.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "deliveredAddressId", referencedColumnName = "deliveredAddressId")
    private DeliveryAddress deliveryAddress;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private ProductData productData;
    @Column(name = "orderStatus")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Column(name = "orderDate")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Column(name = "deliveryDate")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
    @Column(name = "deliveryStatus")
    private Boolean deliveryStatus;
    @Temporal(TemporalType.DATE)
    @Column(name = "cancelDate")
    private Date cancelDate;
    @Column(name = "cancelStatus")
    private Boolean cancelStatus;
    @Column(name = "receivedDate")
    @Temporal(TemporalType.DATE)
    private Date receivedDate;
}
