package com.ecommerce.app.debodelivery.controller;

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
    public UserResponse addUser(@RequestBody UserRequest userRequest){
        return this.userService.addUser(userRequest);
    }



    }

