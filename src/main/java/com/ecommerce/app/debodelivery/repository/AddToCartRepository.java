package com.ecommerce.app.debodelivery.repository;

import com.ecommerce.app.debodelivery.entity.AddToCart;
import com.ecommerce.app.debodelivery.entity.ProductData;
import com.ecommerce.app.debodelivery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddToCartRepository extends JpaRepository<AddToCart, String> {
    public Boolean existsAddToCartByProductDataAndUser(ProductData productData, User user);

    public Boolean existsAddToCartByUser(User user);

    public AddToCart findByProductDataAndUser(ProductData productData, User user);

    public List<AddToCart> findByUser(User user);
}