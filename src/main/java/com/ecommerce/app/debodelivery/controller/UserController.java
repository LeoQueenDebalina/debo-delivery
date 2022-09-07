package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.AuthRequest;
import com.ecommerce.app.debodelivery.model.UserRequest;
import com.ecommerce.app.debodelivery.model.UserResponse;
import com.ecommerce.app.debodelivery.service.UserService;
import com.ecommerce.app.debodelivery.util.JwtUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Api
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

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

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
}

