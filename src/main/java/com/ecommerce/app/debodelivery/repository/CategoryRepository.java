package com.ecommerce.app.debodelivery.repository;

import com.ecommerce.app.debodelivery.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    public Boolean existsCategoryByCategoryNameContaining(String categoryName);
    public Category findByCategoryNameContaining(String categoryName);
}