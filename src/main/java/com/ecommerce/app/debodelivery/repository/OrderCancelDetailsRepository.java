package com.ecommerce.app.debodelivery.repository;

import com.ecommerce.app.debodelivery.entity.OrderCancelDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCancelDetailsRepository extends JpaRepository<OrderCancelDetails,String> {
}
