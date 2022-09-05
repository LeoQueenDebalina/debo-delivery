package com.ecommerce.app.debodelivery.repository;

import com.ecommerce.app.debodelivery.entity.DeliveryAddress;
import com.ecommerce.app.debodelivery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress,String> {
    @Query("select case when count(u)>0 then true else false end from DeliveryAddress u where u.user=:n")
    public Boolean ifAddressAlreadyExist(@Param("n") User user);
    @Query("select u from DeliveryAddress u where u.user=:n")
    public DeliveryAddress findAddress(@Param("n") User user);
}
