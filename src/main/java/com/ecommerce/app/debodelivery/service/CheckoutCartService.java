package com.ecommerce.app.debodelivery.service;

import com.ecommerce.app.debodelivery.repository.AddToCartRepository;
import com.ecommerce.app.debodelivery.repository.CheckOutRepository;
import com.ecommerce.app.debodelivery.repository.ProductDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
