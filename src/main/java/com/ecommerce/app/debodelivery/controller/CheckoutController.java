package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.*;
import com.ecommerce.app.debodelivery.service.CheckoutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Checkout")
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;

    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "This method is used to buy a product.")
    @PostMapping("/buyProduct")
    public ApiResponse buyProduct(@Valid @RequestBody OrderedProductRequest orderedProductRequest) {
        return this.checkoutService.buyProduct(orderedProductRequest);
    }

    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "This method is used to order from cart.")
    @PostMapping("/orderProductFromCart")
    public ApiResponse orderProductFromCart(@Valid @RequestBody CartProductOrderedRequest cartProductOrderedRequest) {
        return this.checkoutService.orderProductFromCart(cartProductOrderedRequest);
    }

    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "This method is used to give feedback for used product.")
    @PostMapping("/orderProductFeedBack")
    public ApiResponse orderProductFeedBack(@Valid @RequestBody OrderProductFeedBackRequest orderProductFeedBackRequest) {
        return this.checkoutService.OrderProductFeedBack(orderProductFeedBackRequest);
    }

    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "This method is used to get order product by mobile number.")
    @GetMapping("/getOrderedProduct/mobileNumber/{mobileNumber}")
    public List<OrderedProductResponse> getOrderedProduct(@PathVariable @ApiParam(name = "Mobile Number", value = "Mobile Number", example = "123456789", required = true) String mobileNumber) throws DataNotFoundException {
        return this.checkoutService.getOrderedProduct(mobileNumber);
    }

    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "This method is used to cancel order.")
    @DeleteMapping("/cancelOrder")
    public ApiResponse cancelOrder(@Valid @RequestBody OrderCancelRequest orderCancelRequest) {
        return this.checkoutService.cancelOrder(orderCancelRequest);
    }
}
