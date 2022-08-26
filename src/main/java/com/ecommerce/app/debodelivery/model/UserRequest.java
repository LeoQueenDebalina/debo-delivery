package com.ecommerce.app.debodelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
//    @NotBlank(message = "")
    private String userName;
//    @NotBlank(message = "")
//    @Email(message = "")
    private String userEmail;
//    @NotBlank(message = "")
    private String password;
//    @NotBlank(message = "")
    private String createdAt;
//    @NotBlank(message = "")
    private String loginToken;
//    @NotBlank(message = "")
    private String address;
//    @NotBlank(message = "")
    private String mobileNumber;
}
