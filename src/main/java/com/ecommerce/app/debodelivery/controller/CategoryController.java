package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.CategoryRequest;
import com.ecommerce.app.debodelivery.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "This method is used to add new category.")
    @PostMapping("/addCategory")
    public ApiResponse addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return this.categoryService.addCategory(categoryRequest);
    }

    @ApiOperation(value = "This method is used to get all category.")
    @GetMapping("/getAllCategory")
    public List<String> getAllCategory() throws DataNotFoundException {
        return this.categoryService.getAllCategory();
    }

    @ApiOperation(value = "This method is used to update category.")
    @PutMapping("/updateCategory/categoryOldName/{categoryOldName}/categoryUpdatedName/{categoryUpdatedName}")
    public ApiResponse updateCategory(@PathVariable String categoryOldName, @PathVariable String categoryUpdatedName) {
        return this.categoryService.updateCategory(categoryOldName, categoryUpdatedName);
    }
}
