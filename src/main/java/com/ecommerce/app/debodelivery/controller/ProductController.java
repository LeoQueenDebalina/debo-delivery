package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.ProductDataIo;
import com.ecommerce.app.debodelivery.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ApiResponse addProduct(@RequestBody ProductDataIo productDataRequest) throws FileNotFoundException, IOException {
        return this.productService.addProduct(productDataRequest);
    }

    @GetMapping("/getAllProduct")
    public List<ProductDataIo> getAllProduct() throws DataNotFoundException {
        return this.productService.getAllProduct();
    }

    @GetMapping("/getProductByName/{name}")
    public List<ProductDataIo> getProductByName(@PathVariable String name) throws DataNotFoundException {
        return this.productService.getProductByName(name);
    }

    @GetMapping("/getProductByCategoryName/{name}")
    public List<ProductDataIo> getProductByCategoryName(@PathVariable String name) throws DataNotFoundException {
        return this.productService.getProductByCategoryName(name);
    }

    @GetMapping("/getProductByMaxPrice/{maxPrices}")
    public List<ProductDataIo> getProductByMaxPrice(@PathVariable Integer maxPrices) throws DataNotFoundException {
        return this.productService.getProductByMaxPrice(maxPrices);
    }
}
