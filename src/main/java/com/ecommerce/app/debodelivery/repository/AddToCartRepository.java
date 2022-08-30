package com.ecommerce.app.debodelivery.repository;

import com.ecommerce.app.debodelivery.entity.AddToCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddToCartRepository extends JpaRepository<AddToCart,String> {
//    @Query("select sum(u.price) from AddToCart where u.userId=:userId")
//    public String getTotalAmountByUserId(@Param("userId") String userId);
//    @Query("select u from AddToCart u where u.productId = :productId and u.userId = :userId")
//    public List<AddToCart> getCartByProductIdAndUserId(@Param("productId") String productId, @Param("userId") String userId);
}