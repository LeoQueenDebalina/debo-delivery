package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.DeliveryAddressRequest;
import com.ecommerce.app.debodelivery.model.DeliveryAddressResponse;
import com.ecommerce.app.debodelivery.service.DeliveredAddressService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Api
public class DeliveryAddressController {
    @Autowired
    private DeliveredAddressService deliveredAddressService;

    @GetMapping("/viewAddress/{userPhone}")
    public DeliveryAddressResponse getDeliveryAddressByUserPhoneNumber(@PathVariable String userPhone) throws DataNotFoundException {
        return this.deliveredAddressService.getDeliveryAddressByUserPhoneNumber(userPhone);
    }

    @PostMapping("/addAddress")
    public ApiResponse addAddress(@RequestBody DeliveryAddressRequest deliveryAddressRequest) {
        return this.deliveredAddressService.addAddress(deliveryAddressRequest);
    }

    @PutMapping("/updateAddress")
    public ApiResponse updateAddress(@RequestBody DeliveryAddressRequest deliveryAddressRequest) {
        return this.deliveredAddressService.updateAddress(deliveryAddressRequest);
    }
}
