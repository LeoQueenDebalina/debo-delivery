package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.TotalPriceInCartResponse;
import com.ecommerce.app.debodelivery.model.ViewCartResponse;
import com.ecommerce.app.debodelivery.service.CartService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api
public class AddToCartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/viewCart/userMobileNumber/{userMobileNumber}")
    public List<ViewCartResponse> viewCart(@PathVariable String userMobileNumber) throws DataNotFoundException {
        return this.cartService.viewCart(userMobileNumber);
    }

    @PostMapping("/addProductToCart/userMobileNumber/{userMobileNumber}/productId/{productId}")
    public ApiResponse addProductToCart(@PathVariable String userMobileNumber, @PathVariable String productId) {
        return this.cartService.addProductToCart(userMobileNumber, productId);
    }

    @GetMapping("/totalPriceInCart/userPhoneNumber/{userPhoneNumber}")
    public TotalPriceInCartResponse totalPriceInCart(@PathVariable String userPhoneNumber) throws DataNotFoundException {
        return this.cartService.totalPriceInCart(userPhoneNumber);
    }

    @DeleteMapping("/deleteProductFromCart/userMobileNumber/{userMobileNumber}/productId/{productId}")
    public ApiResponse deleteProductFromCart(@PathVariable String userMobileNumber, @PathVariable String productId) {
        return this.cartService.deleteProductFromCart(userMobileNumber, productId);
    }
}
