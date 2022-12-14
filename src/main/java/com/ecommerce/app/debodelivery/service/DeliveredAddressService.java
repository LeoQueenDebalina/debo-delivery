package com.ecommerce.app.debodelivery.service;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.entity.DeliveryAddress;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.DeliveryAddressRequest;
import com.ecommerce.app.debodelivery.model.DeliveryAddressResponse;
import com.ecommerce.app.debodelivery.repository.DeliveryAddressRepository;
import com.ecommerce.app.debodelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import java.util.UUID;

@Service
public class DeliveredAddressService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;

    public DeliveryAddressResponse getDeliveryAddressByUserPhoneNumber(String userPhone) throws DataNotFoundException {
        if (userRepository.existsUserByMobileNumber(userPhone)) {
            DeliveryAddress data = this.deliveryAddressRepository.findByUser(userRepository.findByMobileNumber(userPhone));
            if (data != null) {
                return new DeliveryAddressResponse(data.getDeliveredAddressId(),
                        data.getFullName(),
                        data.getPhoneNumber(),
                        data.getPinCode(),
                        data.getState(),
                        data.getCity(),
                        data.getHouseNo(),
                        data.getRoadName(),
                        data.getAddressType());
            } else {
                throw new DataNotFoundException("Address not found");
            }
        } else {
            throw new DataNotFoundException("User not found");
        }
    }

    public ApiResponse addAddress(DeliveryAddressRequest deliveryAddress) {
        if (userRepository.existsUserByMobileNumber(deliveryAddress.getUserPhoneNumber())) {
            if (!deliveryAddressRepository.existsDeliveryAddressByUser(userRepository.findByMobileNumber(deliveryAddress.getUserPhoneNumber()))) {
                this.deliveryAddressRepository.save(DeliveryAddress.builder()
                        .deliveredAddressId(UUID.randomUUID().toString())
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
                        .isDeleted(false)
                        .build());
                return new ApiResponse(false, "Address added successfully");
            } else {
                return new ApiResponse(true, "Your address is already exist");
            }
        } else {
            return new ApiResponse(true, "User not found");
        }
    }


    public ApiResponse updateAddress(DeliveryAddressRequest deliveryAddress) {
        if (userRepository.existsUserByMobileNumber(deliveryAddress.getUserPhoneNumber())) {
            if (deliveryAddressRepository.existsDeliveryAddressByUser(userRepository.findByMobileNumber(deliveryAddress.getUserPhoneNumber()))) {
                DeliveryAddress newData = new DeliveryAddress();
                DeliveryAddress oldData = this.deliveryAddressRepository.findByUser(userRepository.findByMobileNumber(deliveryAddress.getUserPhoneNumber()));
                newData.setDeliveredAddressId(oldData.getDeliveredAddressId());
                if (deliveryAddress.getFullName() != "") {
                    newData.setFullName(deliveryAddress.getFullName());
                } else {
                    newData.setFullName(oldData.getFullName());
                }
                if (deliveryAddress.getPhoneNumber() != "") {
                    newData.setPhoneNumber(deliveryAddress.getPhoneNumber());
                } else {
                    newData.setPhoneNumber(oldData.getPhoneNumber());
                }

                if (deliveryAddress.getPinCode() != 0) {
                    newData.setPinCode(deliveryAddress.getPinCode());
                } else {
                    newData.setPinCode(oldData.getPinCode());
                }
                if (deliveryAddress.getState() != "") {
                    newData.setState(deliveryAddress.getState());
                } else {
                    newData.setState(oldData.getState());
                }
                if (deliveryAddress.getCity() != "") {
                    newData.setCity(deliveryAddress.getCity());
                } else {
                    newData.setCity(oldData.getCity());
                }
                if (deliveryAddress.getHouseNo() != "") {
                    newData.setHouseNo(deliveryAddress.getHouseNo());
                } else {
                    newData.setHouseNo(oldData.getHouseNo());
                }
                if (deliveryAddress.getRoadName() != "") {
                    newData.setRoadName(deliveryAddress.getRoadName());
                } else {
                    newData.setRoadName(oldData.getRoadName());
                }
                if (String.valueOf(deliveryAddress.getAddressType()) != "") {
                    newData.setAddressType(deliveryAddress.getAddressType());
                } else {
                    newData.setAddressType(oldData.getAddressType());
                }
                newData.setUser(oldData.getUser());
                newData.setDate(oldData.getDate());
                this.deliveryAddressRepository.save(newData);
                return new ApiResponse(false, "Information updated successfully");
            } else {
                return new ApiResponse(true, "Address not found");
            }
        } else {
            return new ApiResponse(true, "User not found");
        }
    }
}