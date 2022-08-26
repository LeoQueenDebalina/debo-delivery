package com.ecommerce.app.debodelivery.repository;

import com.ecommerce.app.debodelivery.entity.AddToCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddToCartRepository extends JpaRepository<AddToCart,String> {
}
