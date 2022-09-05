package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.service.CartService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Api
public class AddToCartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/addProductToCart/userMobileNumber/{userMobileNumber}/productId/{productId}")
    public ApiResponse addProductToCart(@PathVariable String userMobileNumber,@PathVariable String productId) {
        return this.cartService.addProductToCart(userMobileNumber, productId);
    }
    @DeleteMapping("/deleteProductFromCart/userMobileNumber/{userMobileNumber}/productId/{productId}")
    public ApiResponse deleteProductFromCart(@PathVariable String userMobileNumber,@PathVariable String productId) {
        return this.cartService.deleteProductFromCart(userMobileNumber, productId);
    }
}
