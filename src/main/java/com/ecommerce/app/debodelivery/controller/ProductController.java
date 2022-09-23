package com.ecommerce.app.debodelivery.controller;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.PageOfProductDataResponse;
import com.ecommerce.app.debodelivery.model.ProductDataRequest;
import com.ecommerce.app.debodelivery.model.ProductDataResponse;
import com.ecommerce.app.debodelivery.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @ApiOperation(value = "This method is used to add new product.")
    @PostMapping("/addProduct")
    public ApiResponse addProduct(@Valid @RequestBody ProductDataRequest productDataRequest) throws FileNotFoundException, IOException {
        return this.productService.addProduct(productDataRequest);
    }

    @ApiOperation(value = "This method is used to get all product.")
    @GetMapping("/getAllProduct")
    public List<ProductDataResponse> getAllProduct() throws DataNotFoundException {
        return this.productService.getAllProduct();
    }

    @ApiOperation(value = "This method is used to get all product by name.")
    @GetMapping("/getProductByName/{name}")
    public List<ProductDataResponse> getProductByName(@PathVariable String name) throws DataNotFoundException {
        return this.productService.getProductByName(name);
    }

    @ApiOperation(value = "This method is used to get all product by category name.")
    @GetMapping("/getProductByCategoryName/{name}")
    public List<ProductDataResponse> getProductByCategoryName(@PathVariable String name) throws DataNotFoundException {
        return this.productService.getProductByCategoryName(name);
    }

    @ApiOperation(value = "This method is used to get all product by max price.")
    @GetMapping("/getProductByMaxPrice/{maxPrices}")
    public List<ProductDataResponse> getProductByMaxPrice(@PathVariable Integer maxPrices) throws DataNotFoundException {
        return this.productService.getProductByMaxPrice(maxPrices);
    }


    @ApiOperation(value = "This method is used to get all using pade number and page size.")
    @GetMapping("/getProductByGivenRange/page/{page}/size/{size}")
    public PageOfProductDataResponse getProductByGivenRange(@PathVariable Integer page, @PathVariable Integer size) throws DataNotFoundException {
        return this.productService.getProductByGivenRange(page, size);
    }

    @ApiOperation(value = "This method is used to get image gaven by image id.")
    @GetMapping("/productImageFindById/{imageId}")
    public ResponseEntity<?> imageFindById(@PathVariable String imageId) throws FileNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(this.productService.imageData(imageId));
    }

    @ApiOperation(value = "This method is used to get product in accenting order by field.")
    @GetMapping("/getAllProductBySorting/{field}")
    public List<ProductDataResponse> findProductsWithSorting(@PathVariable String field) throws DataNotFoundException {
        return this.productService.findProductsWithSorting(field);
    }
}
