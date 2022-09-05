package com.ecommerce.app.debodelivery.service;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.entity.DeliveryAddress;
import com.ecommerce.app.debodelivery.entity.User;
import com.ecommerce.app.debodelivery.model.DeliveryAddressRequest;
import com.ecommerce.app.debodelivery.model.UserRequest;
import com.ecommerce.app.debodelivery.repository.DeliveryAddressRepository;
import com.ecommerce.app.debodelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeliveredAddressService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;

    public ApiResponse addAddress(DeliveryAddressRequest deliveryAddress) {
        UUID uuid = UUID.randomUUID();
        if (userRepository.ifNumberIsExist(deliveryAddress.getUserPhoneNumber())) {
            if (!deliveryAddressRepository.ifAddressAlreadyExist(userRepository.findByMobileNumber(deliveryAddress.getUserPhoneNumber()))) {
                this.deliveryAddressRepository.save(DeliveryAddress.builder()
                        .deliveredAddressId(String.valueOf(uuid))
                        .user(userRepository.findByMobileNumber(deliveryAddress.getUserPhoneNumber()))
                        .fullName(deliveryAddress.getFullName())
                        .phoneNumber(deliveryAddress.getPhoneNumber())
                        .pinCode(deliveryAddress.getPinCode())
                        .state(deliveryAddress.getState())
                        .city(deliveryAddress.getCity())
                        .houseNo(deliveryAddress.getHouseNo())
                        .roadName(deliveryAddress.getRoadName())
                        .addressType(deliveryAddress.getAddressType())
                        .date(new Date())
                        .build());
                return new ApiResponse(false, "Address added successfully");
            } else {
                return new ApiResponse(true, "Your address is already exist,Please delete the current address and Add new address");
            }
        } else {
            return new ApiResponse(true, "User not found");
        }
    }

    public ApiResponse deleteAddress(String phoneNumber) {
        if (userRepository.ifNumberIsExist(phoneNumber)) {
            if (deliveryAddressRepository.ifAddressAlreadyExist(userRepository.findByMobileNumber(phoneNumber))) {
                   DeliveryAddress data = this.deliveryAddressRepository.findAddress(userRepository.findByMobileNumber(phoneNumber));
                   deliveryAddressRepository.deleteById(data.getDeliveredAddressId());
                return new ApiResponse(false, "Address deleted successfully");
            } else {
                return new ApiResponse(true, "Your address is not exist, Please add new address");
            }
        } else {
            return new ApiResponse(true, "User not found");
        }
    }
    public ApiResponse updateAddress(DeliveryAddressRequest deliveryAddress) {
        if (userRepository.ifNumberIsExist(deliveryAddress.getUserPhoneNumber())) {
            if (deliveryAddressRepository.ifAddressAlreadyExist(userRepository.findByMobileNumber(deliveryAddress.getUserPhoneNumber()))) {
                {
            DeliveryAddress newData = new DeliveryAddress();

            DeliveryAddress oldData = this.deliveryAddressRepository.findAddress(userRepository.findByMobileNumber(deliveryAddress.getUserPhoneNumber()));
            newData.setDeliveredAddressId(oldData.getDeliveredAddressId());
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

}
