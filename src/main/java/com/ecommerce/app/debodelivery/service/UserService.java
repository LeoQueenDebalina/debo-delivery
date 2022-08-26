package com.ecommerce.app.debodelivery.service;

import com.ecommerce.app.debodelivery.entity.User;
import com.ecommerce.app.debodelivery.model.UserRequest;
import com.ecommerce.app.debodelivery.model.UserResponse;
import com.ecommerce.app.debodelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public UserResponse addUser(UserRequest userRequest){
        UUID uuid = UUID.randomUUID();
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        System.out.println(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        this.userRepository.save(User.builder().userId(String.valueOf(uuid))
                .userName(userRequest.getUserName())
                        .userEmail(userRequest.getUserEmail())
                        .password(userRequest.getPassword())
                        .createdAt(userRequest.getCreatedAt())
                        .loginToken(userRequest.getLoginToken())
                        .type(Type.USER).address(userRequest.getAddress())
                        .isEmailVerified("True")
                        .mobileNumber(userRequest.getMobileNumber())
                .build());
        return new UserResponse();
    }
}
