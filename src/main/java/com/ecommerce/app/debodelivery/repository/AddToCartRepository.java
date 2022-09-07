package com.ecommerce.app.debodelivery.repository;

import com.ecommerce.app.debodelivery.entity.AddToCart;
import com.ecommerce.app.debodelivery.entity.ProductData;
import com.ecommerce.app.debodelivery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddToCartRepository extends JpaRepository<AddToCart, String> {
    @Query("select case when count(u)>0 then true else false end from AddToCart u where u.productData=:n and u.user=:m")
    public Boolean isExistCart(@Param("n") ProductData productData, @Param("m") User user);

    @Query("select case when count(u)>0 then true else false end from AddToCart u where u.user=:m")
    public Boolean isExistCartCheckByUser(@Param("m") User user);

    @Query("select u from AddToCart u where u.productData=:n and u.user=:m")
    public AddToCart selectByProductData(@Param("n") ProductData productData, @Param("m") User user);

    @Query("select u from AddToCart u where u.user=:m")
    public List<AddToCart> selectAllCartDataByUser(@Param("m") User user);
}