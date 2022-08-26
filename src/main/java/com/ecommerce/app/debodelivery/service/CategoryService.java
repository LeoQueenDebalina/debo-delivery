package com.ecommerce.app.debodelivery.service;

import com.ecommerce.app.debodelivery.entity.Category;
import com.ecommerce.app.debodelivery.model.CategoryRequest;
import com.ecommerce.app.debodelivery.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public Map<String, String> addCategory(CategoryRequest categoryRequest){
        UUID uuid = UUID.randomUUID();
        Map<String, String> response = new HashMap<>();
        System.out.println(this.categoryRepository.findByCategory(categoryRequest.getCategoryName()));
        if (!this.categoryRepository.findByCategory(categoryRequest.getCategoryName())) {
            response.put("error","false");
            response.put("message","category add successful");
            this.categoryRepository.save(Category.builder().categoryId(String.valueOf(uuid)).categoryName(categoryRequest.getCategoryName()).build());
        } else {
            response.put("error","false");
            response.put("message","category already added");
        }
        return response;
    }
}
