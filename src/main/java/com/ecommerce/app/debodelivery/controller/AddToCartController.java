package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.TotalPriceInCartResponse;
import com.ecommerce.app.debodelivery.model.ViewCartResponse;
import com.ecommerce.app.debodelivery.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Cart")
public class AddToCartController {
    @Autowired
    private CartService cartService;

    @ApiOperation(value = "This method is used to view cart product.")
    @GetMapping("/viewCart/userMobileNumber/{userMobileNumber}")
    public List<ViewCartResponse> viewCart(@PathVariable @ApiParam(name = "Mobile Number", value = "Mobile Number", example = "123456789", required = true) String userMobileNumber) throws DataNotFoundException {
        return this.cartService.viewCart(userMobileNumber);
    }

    @ApiOperation(value = "This method is used to add to cart product.")
    @PostMapping("/addProductToCart/userMobileNumber/{userMobileNumber}/productId/{productId}")
    public ApiResponse addProductToCart(@PathVariable @ApiParam(name = "Mobile Number", value = "Mobile Number", example = "123456789", required = true) String userMobileNumber, @PathVariable @ApiParam(name = "Product Id", value = "Product Id", required = true) String productId) {
        return this.cartService.addProductToCart(userMobileNumber, productId);
    }

    @ApiOperation(value = "This method is used to get count of total price in cart product.")
    @GetMapping("/totalPriceInCart/userPhoneNumber/{userPhoneNumber}")
    public TotalPriceInCartResponse totalPriceInCart(@PathVariable @ApiParam(name = "Mobile Number", value = "Mobile Number", example = "123456789", required = true) String userPhoneNumber) throws DataNotFoundException {
        return this.cartService.totalPriceInCart(userPhoneNumber);
    }

    @ApiOperation(value = "This method is used to remove product from cart.")
    @DeleteMapping("/deleteProductFromCart/userMobileNumber/{userMobileNumber}/productId/{productId}")
    public ApiResponse deleteProductFromCart(@PathVariable @ApiParam(name = "Mobile Number", value = "Mobile Number", example = "123456789", required = true) String userMobileNumber, @PathVariable @ApiParam(name = "Product Id", value = "Product Id", required = true) String productId) {
        return this.cartService.deleteProductFromCart(userMobileNumber, productId);
    }
}
