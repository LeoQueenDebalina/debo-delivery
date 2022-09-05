package com.ecommerce.app.debodelivery.model;

import com.ecommerce.app.debodelivery.eenum.OrderStatus;
import com.ecommerce.app.debodelivery.eenum.PaymentType;
import com.ecommerce.app.debodelivery.entity.DeliveryAddress;
import com.ecommerce.app.debodelivery.entity.ProductData;

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
    private DeliveryAddress deliveryAddress;
    private ProductData productData;
    private OrderStatus orderStatus;
    private Date orderDate;
    private Date deliveryDate;
    private Boolean deliveryStatus;
    private Date cancelDate;
    private Boolean cancelStatus;
    private Date receivedDate;
}
