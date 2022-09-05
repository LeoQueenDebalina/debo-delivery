package com.ecommerce.app.debodelivery.service;

import com.ecommerce.app.debodelivery.model.OrderedProductRequest;
import com.ecommerce.app.debodelivery.model.OrderedProductResponse;
import com.ecommerce.app.debodelivery.repository.AddToCartRepository;
import com.ecommerce.app.debodelivery.repository.CheckOutRepository;
import com.ecommerce.app.debodelivery.repository.ProductDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutCartService {
    @Autowired
    private CheckOutRepository checkOutRepository;
    @Autowired
    private AddToCartRepository addToCartRepository;
    @Autowired
    private ProductDataRepository productDataRepository;
    @Autowired
    private UserService userService;

    public List<OrderedProductResponse> getAllOrderedProductsByUserPhone(OrderedProductRequest orderedProductRequest) {
        return null;
    }

}
