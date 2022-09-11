package com.ecommerce.app.debodelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @Pattern(regexp = "[A-Za-z ]+", message = "should contain character and space only")
    @NotBlank
    private String userName;
    @Email
    @NotBlank
    private String userEmail;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character")
    @NotBlank
    private String password;
    @Pattern(regexp = "[A-Za-z .,]+$", message = "should contain character, dot and space only")
    @NotBlank
    private String address;
    @Pattern(regexp = "[\\d]+" ,message = "Should contain digits only")
    @Size(min = 10, max = 10)
    @NotBlank
    private String mobileNumber;
}
