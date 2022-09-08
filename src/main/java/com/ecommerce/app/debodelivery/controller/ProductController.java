package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.ProductDataRequest;
import com.ecommerce.app.debodelivery.model.ProductDataResponse;
import com.ecommerce.app.debodelivery.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ApiResponse addProduct(@RequestBody ProductDataRequest productDataRequest) throws FileNotFoundException, IOException {
        return this.productService.addProduct(productDataRequest);
    }

    @GetMapping("/getAllProduct")
    public List<ProductDataResponse> getAllProduct() throws DataNotFoundException {
        return this.productService.getAllProduct();
    }

    @GetMapping("/getProductByName/{name}")
    public List<ProductDataResponse> getProductByName(@PathVariable String name) throws DataNotFoundException {
        return this.productService.getProductByName(name);
    }

    @GetMapping("/getProductByCategoryName/{name}")
    public List<ProductDataResponse> getProductByCategoryName(@PathVariable String name) throws DataNotFoundException {
        return this.productService.getProductByCategoryName(name);
    }

    @GetMapping("/getProductByMaxPrice/{maxPrices}")
    public List<ProductDataResponse> getProductByMaxPrice(@PathVariable Integer maxPrices) throws DataNotFoundException {
        return this.productService.getProductByMaxPrice(maxPrices);
    }

    @GetMapping("/getProductByGivenRange/minRange/{minRange}/maxRange/{maxRange}")
    public List<ProductDataResponse> getProductByGivenRange(@PathVariable Integer minRange, @PathVariable Integer maxRange) throws DataNotFoundException {
        return this.productService.getProductByGivenRange(minRange, maxRange);
    }

    @GetMapping("/productImageFindById/{imageId}")
    public ResponseEntity<?> imageFindById(@PathVariable String imageId) throws FileNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(this.productService.imageData(imageId));
    }

    @GetMapping("/getAllProductBySorting/{field}")
    public List<ProductDataResponse> findProductsWithSorting(@PathVariable String field) throws DataNotFoundException {
        return this.productService.findProductsWithSorting(field);
    }
}
