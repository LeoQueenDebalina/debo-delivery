package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.ProductDataRequest;
import com.ecommerce.app.debodelivery.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ApiResponse addProduct(@RequestBody ProductDataRequest productDataRequest) {
        return this.productService.addProduct(productDataRequest);
    }
    @GetMapping("/getAllProduct")
    public List<ProductDataRequest> getAllProduct() throws DataNotFoundException {
        return this.productService.getAllProduct();
    }
    @GetMapping("/getProductByName/{name}")
    public List<ProductDataRequest> getProductByName(@PathVariable String name) throws DataNotFoundException {
        return this.productService.getProductByName(name);
    }

}
