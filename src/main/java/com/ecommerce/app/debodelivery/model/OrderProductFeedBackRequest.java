package com.ecommerce.app.debodelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductFeedBackRequest {
    @Pattern(regexp = "[\\d]+" ,message = "Should contain digits only")
    @Size(min = 10, max = 10)
    @NotBlank
    private String userMobileNumber;
    @NotBlank
    private String checkoutId;
    @NotBlank
    private String feedbackMessage;
}
