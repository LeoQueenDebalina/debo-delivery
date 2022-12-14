package com.ecommerce.app.debodelivery.model;

import com.ecommerce.app.debodelivery.helper.AddressType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressRequest {
    @Pattern(regexp = "[\\d]+" ,message = "Should contain digits only")
    @Size(min = 10, max = 10)
    @NotBlank
    private String userPhoneNumber;
    @Pattern(regexp = "[A-Za-z ]+", message = "should contain character and space only")
    private String fullName;
    @Pattern(regexp = "[\\d]+" ,message = "Should contain digits only")
    @Size(min = 10, max = 10)
    @NotBlank
    private String phoneNumber;
    @Size(min = 6, max = 6)
    @NotNull
    private Integer pinCode;
    @Pattern(regexp = "[A-Za-z .]+", message = "should contain character, dot and space only")
    @NotBlank
    private String state;
    @Pattern(regexp = "[A-Za-z .]+", message = "should contain character, dot and space only")
    @NotBlank
    private String city;
    @Pattern(regexp = "[A-Za-z\\d .]+", message = "should contain character, dot and space only")
    @NotBlank
    private String houseNo;
    @Pattern(regexp = "[A-Za-z\\d .]+", message = "should contain character, dot and space only")
    @NotBlank
    private String roadName;
    @NotNull
    private AddressType addressType;
}
