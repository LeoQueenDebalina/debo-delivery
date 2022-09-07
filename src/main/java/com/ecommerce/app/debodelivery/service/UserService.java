package com.ecommerce.app.debodelivery.service;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.util.Type;
import com.ecommerce.app.debodelivery.entity.User;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.UserRequest;
import com.ecommerce.app.debodelivery.model.UserResponse;
import com.ecommerce.app.debodelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public ApiResponse addUser(UserRequest userRequest) {
        UUID uuid = UUID.randomUUID();
        if (!userRepository.ifNumberIsExist(userRequest.getMobileNumber())) {
            this.userRepository.save(User.builder()
                    .userId(String.valueOf(uuid))
                    .userName(userRequest.getUserName())
                    .userEmail(userRequest.getUserEmail())
                    .password(bCryptPasswordEncoder.encode(userRequest.getPassword()))
                    .createdAt(new Date())
                    .loginToken(userRequest.getLoginToken())
                    .type(Type.USER)
                    .address(userRequest.getAddress())
                    .isEmailVerified(true)
                    .mobileNumber(userRequest.getMobileNumber())
                    .isDeleted(false)
                    .build());
            return new ApiResponse(false, "User successfully register");
        } else {
            return new ApiResponse(true, "This phone number is already exist");
        }
    }

    public ApiResponse updateUserData(UserRequest userRequest) {
        if (userRepository.ifNumberIsExist(userRequest.getMobileNumber()) && !userRepository.getUserIsDeletedByNumber(userRequest.getMobileNumber())) {
            User newData = new User();
            String id = this.userRepository.getUserIdByNumber(userRequest.getMobileNumber());
            Optional<User> oldData = this.userRepository.findById(id);
            newData.setUserId(id);
            if (userRequest.getUserName() != "") {
                newData.setUserName(userRequest.getUserName());
            } else {
                newData.setUserName(oldData.get().getUserName());
            }
            if (userRequest.getUserEmail() != "") {
                newData.setUserEmail(userRequest.getUserEmail());
            } else {
                newData.setUserEmail(oldData.get().getUserEmail());
            }
            newData.setMobileNumber(oldData.get().getMobileNumber());
            if (userRequest.getPassword() != "") {
                newData.setPassword(userRequest.getPassword());
            } else {
                newData.setPassword(oldData.get().getPassword());
            }
            newData.setCreatedAt(oldData.get().getCreatedAt());
            newData.setLoginToken(oldData.get().getLoginToken());
            newData.setType(oldData.get().getType());
            if (userRequest.getAddress() != "") {
                newData.setAddress(userRequest.getAddress());
            } else {
                newData.setAddress(oldData.get().getAddress());
            }
            newData.setIsEmailVerified(oldData.get().getIsEmailVerified());
            newData.setIsDeleted(oldData.get().getIsDeleted());
            this.userRepository.save(newData);
            return new ApiResponse(false, "information updated successfully");
        } else {
            return new ApiResponse(true, "mobile number does not exist");
        }
    }

    public ApiResponse deleteUser(String mobileNumber, Boolean cStatus) {
        if (userRepository.ifNumberIsExist(mobileNumber) && cStatus && !userRepository.getUserIsDeletedByNumber(mobileNumber)) {
            this.userRepository.deleteAccount(mobileNumber);
            return new ApiResponse(false, "Your account is successfully deleted");
        } else {
            return new ApiResponse(true, "user not found");
        }
    }

    public UserResponse getUserByMobileNumber(String mobileNumber) throws DataNotFoundException {
        if (userRepository.ifNumberIsExist(mobileNumber) && !userRepository.getUserIsDeletedByNumber(mobileNumber)) {
            String id = this.userRepository.getUserIdByNumber(mobileNumber);
            Optional<User> data = this.userRepository.findById(id);
            return new UserResponse(
                    data.get().getUserName(),
                    data.get().getUserEmail(),
                    data.get().getAddress(),
                    data.get().getMobileNumber());
        } else {
            throw new DataNotFoundException("user not found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByMobileNumber(username);
        return new org.springframework.security.core.userdetails.User(user.getMobileNumber(), user.getPassword(), new ArrayList<>());
    }
}
