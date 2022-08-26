package com.ecommerce.app.debodelivery.repository;


import com.ecommerce.app.debodelivery.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    @Query("select case when count(u)>0 then true else false end from Category u where u.categoryName like %:n%")
    public Boolean findByCategory(@Param("n") String category);
}