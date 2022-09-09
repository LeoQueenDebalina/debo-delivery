package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.*;
import com.ecommerce.app.debodelivery.service.CheckoutService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/buyProduct")
    public ApiResponse buyProduct(@Valid @RequestBody OrderedProductRequest orderedProductRequest) throws DataNotFoundException {
        return this.checkoutService.buyProduct(orderedProductRequest);
    }

    @PostMapping("/orderProductFromCart")
    public ApiResponse orderProductFromCart(@Valid @RequestBody CartProductOrderedRequest cartProductOrderedRequest) throws DataNotFoundException {
        return this.checkoutService.orderProductFromCart(cartProductOrderedRequest);
    }

    @PostMapping("/OrderProductFeedBack")
    public ApiResponse OrderProductFeedBack(@Valid @RequestBody OrderProductFeedBackRequest orderProductFeedBackRequest) throws DataNotFoundException {
        return this.checkoutService.OrderProductFeedBack(orderProductFeedBackRequest);
    }

    @GetMapping("/getOrderedProduct/mobileNumber/{mobileNumber}")
    public List<OrderedProductResponse> getOrderedProduct(@PathVariable String mobileNumber) throws DataNotFoundException {
        return this.checkoutService.getOrderedProduct(mobileNumber);
    }

    @DeleteMapping("/cancelOrder")
    public ApiResponse cancelOrder(@RequestBody OrderCancelRequest orderCancelRequest) {
        return this.checkoutService.cancelOrder(orderCancelRequest);
    }
}
