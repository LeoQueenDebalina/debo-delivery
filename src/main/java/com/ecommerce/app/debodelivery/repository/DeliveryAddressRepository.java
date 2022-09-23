package com.ecommerce.app.debodelivery.repository;

import com.ecommerce.app.debodelivery.entity.DeliveryAddress;
import com.ecommerce.app.debodelivery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, String> {
    public Boolean existsDeliveryAddressByUser(User user);
    public DeliveryAddress findByUser(User user);
}
