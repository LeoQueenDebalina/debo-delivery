package com.ecommerce.app.debodelivery.repository;

import com.ecommerce.app.debodelivery.entity.OrderCancelDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCancelDetailsRepository extends JpaRepository<OrderCancelDetails, String> {
}
