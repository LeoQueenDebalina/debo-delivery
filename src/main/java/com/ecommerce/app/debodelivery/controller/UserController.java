package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.UserRequest;
import com.ecommerce.app.debodelivery.model.UserResponse;
import com.ecommerce.app.debodelivery.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Api
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ApiResponse addUser(@RequestBody UserRequest userRequest) {
        return this.userService.addUser(userRequest);
    }

    @PutMapping("/updateUser")
    public ApiResponse updateUser(@RequestBody UserRequest userRequest) {
        return this.userService.updateUserData(userRequest);
    }

    @DeleteMapping("/deleteUser/{mobileNumber}/status/{status}")
    public ApiResponse deleteUser(@PathVariable("mobileNumber") String mobileNumber, @PathVariable Boolean status) {
        return this.userService.deleteUser(mobileNumber, status);
    }

    @GetMapping("/getUserByMobileNumber/{mobileNumber}")
    public UserResponse getUserByMobileNumber(@PathVariable("mobileNumber") String mobileNumber) throws DataNotFoundException {
        return this.userService.getUserByMobileNumber(mobileNumber);
    }
}

