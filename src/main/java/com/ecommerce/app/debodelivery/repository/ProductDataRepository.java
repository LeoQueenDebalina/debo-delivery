package com.ecommerce.app.debodelivery.repository;

import com.ecommerce.app.debodelivery.entity.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDataRepository extends JpaRepository<ProductData,String> {
    @Query("select u from ProductData u where u.productName like %:n%")
    public List<ProductData> findAllByName(@Param("n") String name);
}
