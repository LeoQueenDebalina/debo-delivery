package com.ecommerce.app.debodelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    @Pattern(regexp = "[a-z ]+", message = "should contain character and space only")
    @NotBlank
    private String categoryName;
}
