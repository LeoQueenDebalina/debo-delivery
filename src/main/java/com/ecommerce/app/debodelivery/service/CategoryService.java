package com.ecommerce.app.debodelivery.service;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.entity.Category;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.CategoryRequest;
import com.ecommerce.app.debodelivery.model.ProductDataRequest;
import com.ecommerce.app.debodelivery.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public ApiResponse addCategory(CategoryRequest categoryRequest){
        UUID uuid = UUID.randomUUID();
        if (!this.categoryRepository.findByCategory(categoryRequest.getCategoryName())) {
            this.categoryRepository.save(Category.builder().categoryId(String.valueOf(uuid)).categoryName(categoryRequest.getCategoryName()).build());
            return new ApiResponse(false,"category add successful");
        } else {
            return new ApiResponse(true,"category already added");
        }
    }
    public List<String> getAllCategory() throws DataNotFoundException {

        List<String> list = new ArrayList<>();
        for (Category data : this.categoryRepository.findAll()) {
            list.add(data.getCategoryName());
        }
        if (list.isEmpty()){
            throw new DataNotFoundException("No cateory found");
        }else {
            return list;
        }
    }
}
