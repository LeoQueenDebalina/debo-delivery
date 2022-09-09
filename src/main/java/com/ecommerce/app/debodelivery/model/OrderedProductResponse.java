package com.ecommerce.app.debodelivery.model;

import com.ecommerce.app.debodelivery.helper.OrderStatus;
import com.ecommerce.app.debodelivery.helper.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProductResponse {
    private String checkoutId;
    private PaymentType paymentType;
    private String deliveryAddressId;
    private String productDataId;
    private OrderStatus orderStatus;
    private Date orderDate;
    private Date deliveryDate;
    private Boolean deliveryStatus;
    private Date cancelDate;
    private Boolean cancelStatus;
    private Date receivedDate;
}
