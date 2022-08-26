package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.model.CategoryRequest;
import com.ecommerce.app.debodelivery.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Api
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public Map<String, String> addCategory(@RequestBody CategoryRequest categoryRequest){
        return this.categoryService.addCategory(categoryRequest);
    }
}
