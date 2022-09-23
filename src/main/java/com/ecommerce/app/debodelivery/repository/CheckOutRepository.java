package com.ecommerce.app.debodelivery.repository;

import com.ecommerce.app.debodelivery.entity.Checkout;
import com.ecommerce.app.debodelivery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckOutRepository extends JpaRepository<Checkout, String> {
    public List<Checkout> findByUser(User user);
    public boolean existsCheckoutByUserAndCheckoutId(User user, String checkoutId);
}
