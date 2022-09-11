package com.ecommerce.app.debodelivery.repository;

import com.ecommerce.app.debodelivery.entity.Category;
import com.ecommerce.app.debodelivery.entity.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductDataRepository extends JpaRepository<ProductData, String> {
    @Query("select u from ProductData u where u.productName like %:n%")
    public List<ProductData> findAllByName(@Param("n") String name);

    @Query("select u from ProductData u where u.category = :n")
    public List<ProductData> findAllByCategoryId(@Param("n") Category category);

    @Query("select u from ProductData u where u.productId = :n")
    public ProductData findProductById(@Param("n") String productId);

    @Query("select u from ProductData u where u.productSellingPrice < :n")
    public List<ProductData> findAllByMaxPrice(@Param("n") Integer maxPrice);

    @Query(value = "select * from product_data limit ?1, ?2", nativeQuery = true)
    public List<ProductData> findAllByRange(Integer startRange, Integer maxRange);

    @Transactional
    @Modifying
    @Query("update ProductData u set u.stock=u.stock-1 where u.productId = :n")
    public Integer removeStock(@Param("n") String productId);

    @Transactional
    @Modifying
    @Query("update ProductData u set u.stock=u.stock+1 where u.productId = :n")
    public Integer addStock(@Param("n") String productId);
}
