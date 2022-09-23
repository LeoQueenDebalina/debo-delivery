package com.ecommerce.app.debodelivery.service;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.entity.Category;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.CategoryRequest;
import com.ecommerce.app.debodelivery.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ApiResponse addCategory(CategoryRequest categoryRequest) {
        UUID uuid = UUID.randomUUID();
        if (!this.categoryRepository.existsCategoryByCategoryNameContaining(categoryRequest.getCategoryName())) {
            this.categoryRepository.save(Category.builder().categoryId(String.valueOf(uuid)).categoryName(categoryRequest.getCategoryName()).build());
            return new ApiResponse(false, "Category add successful");
        } else {
            return new ApiResponse(true, "Category already added");
        }
    }

    public List<String> getAllCategory() throws DataNotFoundException {

        List<String> list = new ArrayList<>();
        for (Category data : this.categoryRepository.findAll()) {
            list.add(data.getCategoryName());
        }
        if (list.isEmpty()) {
            throw new DataNotFoundException("No category found");
        } else {
            return list;
        }
    }

    public ApiResponse updateCategory(String categoryOldName, String categoryUpdatedName) {
        if (!categoryOldName.equals(categoryUpdatedName)) {
            Category data = this.categoryRepository.findByCategoryNameContaining(categoryOldName);
            if (data != null) {
                this.categoryRepository.save(Category.builder()
                        .categoryId(data.getCategoryId())
                        .categoryName(categoryUpdatedName)
                        .build());
                return new ApiResponse(false, "Category updated");
            } else {
                return new ApiResponse(true, "Category not found");
            }
        } else {
            return new ApiResponse(true, "Category name are same");
        }
    }
}
