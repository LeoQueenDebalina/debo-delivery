package com.ecommerce.app.debodelivery.repository;

import com.ecommerce.app.debodelivery.entity.OrderFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFeedbackRepository extends JpaRepository<OrderFeedback, String> {
}
