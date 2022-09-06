package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.OrderCancelRequest;
import com.ecommerce.app.debodelivery.model.OrderProductFeedBackRequest;
import com.ecommerce.app.debodelivery.model.OrderedProductRequest;
import com.ecommerce.app.debodelivery.model.OrderedProductResponse;
import com.ecommerce.app.debodelivery.service.CheckoutService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutCartService;

    @PostMapping("/buyProduct")
    public ApiResponse buyProduct(@RequestBody OrderedProductRequest orderedProductRequest) throws DataNotFoundException {
        return this.checkoutCartService.buyProduct(orderedProductRequest);
    }
    @PostMapping("/OrderProductFeedBack")
    public ApiResponse OrderProductFeedBack(@RequestBody OrderProductFeedBackRequest orderProductFeedBackRequest) throws DataNotFoundException {
        return this.checkoutCartService.OrderProductFeedBack(orderProductFeedBackRequest);
    }
    @GetMapping("/getOrderedProduct/mobileNumber/{mobileNumber}")
    public List<OrderedProductResponse> getOrderedProduct(@PathVariable String mobileNumber) throws DataNotFoundException {
        return this.checkoutCartService.getOrderedProduct(mobileNumber);
    }
    @DeleteMapping("/cancelOrder")
    public ApiResponse cancelOrder(@RequestBody OrderCancelRequest orderCancelRequest) {
        return this.checkoutCartService.cancelOrder(orderCancelRequest);
    }
}
