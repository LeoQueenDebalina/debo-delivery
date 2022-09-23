package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.helper.Type;
import com.ecommerce.app.debodelivery.model.AuthRequest;
import com.ecommerce.app.debodelivery.model.AuthResponse;
import com.ecommerce.app.debodelivery.model.UserRequest;
import com.ecommerce.app.debodelivery.model.UserResponse;
import com.ecommerce.app.debodelivery.service.UserService;
import com.ecommerce.app.debodelivery.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "User")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @ApiOperation(value = "This method is used to add normal user.")
    @PostMapping("/addNormalUser")
    public ApiResponse addNormalUser(@Valid @RequestBody UserRequest userRequest) {
        return this.userService.addUser(userRequest, Type.ROLE_USER);
    }

    @ApiOperation(value = "This method is used to get the user.")
    @PostMapping("/addAdminUser")
    public ApiResponse addAdminUser(@Valid @RequestBody UserRequest userRequest) {
        return this.userService.addUser(userRequest, Type.ROLE_ADMIN);
    }

    @ApiOperation(value = "This method is used to add admin user.")
    @PutMapping("/updateUser")
    public ApiResponse updateUser(@Valid @RequestBody UserRequest userRequest) {
        return this.userService.updateUserData(userRequest);
    }

    @ApiOperation(value = "This method is used to delete the user.")
    @DeleteMapping("/deleteUser/{mobileNumber}/status/{status}")
    public ApiResponse deleteUser(@PathVariable("mobileNumber") String mobileNumber, @PathVariable Boolean status) {
        return this.userService.deleteUser(mobileNumber, status);
    }

    @ApiOperation(value = "This method is used to get the user.")
    @GetMapping("/getUserByMobileNumber/{mobileNumber}")
    public UserResponse getUserByMobileNumber(@PathVariable("mobileNumber") String mobileNumber) throws DataNotFoundException {
        return this.userService.getUserByMobileNumber(mobileNumber);
    }

    @ApiOperation(value = "This method is used to get jwt token.")
    @PostMapping("/authenticate")
    public AuthResponse generateToken(@Valid @RequestBody AuthRequest authRequest) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new BadCredentialsException("invalid username/password");
        }
        if (userService.isEmailVerified(authRequest.getUserName())) {
            throw new BadCredentialsException("Email not verified");
        }
        return new AuthResponse(jwtUtil.generateToken(authRequest.getUserName()));
    }

    @ApiOperation(value = "This method is used to request for verification mail.")
    @PostMapping("/emailVerify/{mobileNumber}")
    public ApiResponse emailVerify(@PathVariable String mobileNumber) throws BadCredentialsException {
        return this.userService.emailVerify(mobileNumber);
    }

    @ApiOperation(value = "This method is used to verify email.")
    @GetMapping("/registration/confirm")
    public ApiResponse generateToken(@RequestParam(value = "token", defaultValue = "null") String token) {
        return this.userService.confirmToken(token);
    }
}

