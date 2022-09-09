package com.ecommerce.app.debodelivery.model;

import com.ecommerce.app.debodelivery.helper.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductOrderedRequest {
    @Pattern(regexp = "[\\d]+" ,message = "Should contain digits only")
    @Size(min = 10, max = 10)
    @NotBlank
    private String userMobileNumber;
    @NotBlank
    private String addressId;
    @NotNull
    private PaymentType paymentType;
}
