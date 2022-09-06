package com.ecommerce.app.debodelivery.repository;

import com.ecommerce.app.debodelivery.entity.Category;
import com.ecommerce.app.debodelivery.entity.Checkout;
import com.ecommerce.app.debodelivery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckOutRepository extends JpaRepository<Checkout, String> {
    @Query("select u from Checkout u where u.user = :n")
    public List<Checkout> findOrderByUser(@Param("n") User user);
    @Query("select case when count(u)>0 then true else false end from Checkout u where u.user=:n and u.checkoutId=:m")
    public boolean orderedProductIsExist(@Param("n") User user,@Param("m") String checkoutId);
}
