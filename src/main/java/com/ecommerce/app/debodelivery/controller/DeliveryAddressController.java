package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.DeliveryAddressRequest;
import com.ecommerce.app.debodelivery.model.DeliveryAddressResponse;
import com.ecommerce.app.debodelivery.service.DeliveredAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Address")
public class DeliveryAddressController {
    @Autowired
    private DeliveredAddressService deliveredAddressService;

    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "This method is used to get address by phone number.")
    @GetMapping("/viewAddress/{userPhone}")
    public DeliveryAddressResponse getDeliveryAddressByUserPhoneNumber(@PathVariable @ApiParam(name = "Mobile Number", value = "Mobile Number", example = "123456789", required = true) String userPhone) throws DataNotFoundException {
        return this.deliveredAddressService.getDeliveryAddressByUserPhoneNumber(userPhone);
    }

    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "This method is used to add new address.")
    @PostMapping("/addAddress")
    public ApiResponse addAddress(@Valid @RequestBody DeliveryAddressRequest deliveryAddressRequest) {
        return this.deliveredAddressService.addAddress(deliveryAddressRequest);
    }

    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "This method is used to update user address.")
    @PutMapping("/updateAddress")
    public ApiResponse updateAddress(@Valid @RequestBody DeliveryAddressRequest deliveryAddressRequest) {
        return this.deliveredAddressService.updateAddress(deliveryAddressRequest);
    }
}
