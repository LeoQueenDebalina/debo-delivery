package com.ecommerce.app.debodelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductFeedBackRequest {
    private String userMobileNumber;
    private String checkoutId;
    private String feedbackMessage;
}
