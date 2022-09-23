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
    public List<ProductData> findByProductNameContaining(String productName);

    public List<ProductData> findByCategory(Category category);

    public ProductData findByProductId(String productId);

    public List<ProductData> findByProductSellingPriceLessThanEqual(Integer productSellingPrice);

    @Transactional
    @Modifying
    @Query("update ProductData u set u.stock=u.stock-1 where u.productId = :n")
    public Integer removeStock(@Param("n") String productId);

    @Transactional
    @Modifying
    @Query("update ProductData u set u.stock=u.stock+1 where u.productId = :n")
    public Integer addStock(@Param("n") String productId);
}
