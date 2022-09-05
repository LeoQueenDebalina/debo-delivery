package com.ecommerce.app.debodelivery.repository;

import com.ecommerce.app.debodelivery.entity.CheckoutCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckOutRepository extends JpaRepository<CheckoutCart, String> {
}
