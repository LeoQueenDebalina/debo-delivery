package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.CategoryRequest;
import com.ecommerce.app.debodelivery.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public ApiResponse addCategory(@RequestBody CategoryRequest categoryRequest){
        return this.categoryService.addCategory(categoryRequest);
    }
    @GetMapping("/getAllCategory")
    public List<String> getAllCategory() throws DataNotFoundException {
        return this.categoryService.getAllCategory();
    }
}
