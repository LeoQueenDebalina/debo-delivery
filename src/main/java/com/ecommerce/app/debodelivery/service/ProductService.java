package com.ecommerce.app.debodelivery.service;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.entity.ProductData;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.ProductDataRequest;
import com.ecommerce.app.debodelivery.repository.CategoryRepository;
import com.ecommerce.app.debodelivery.repository.ProductDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductDataRepository productDataRepository;
    public ApiResponse addProduct(ProductDataRequest productDataRequest){
        UUID uuid = UUID.randomUUID();
        if (this.categoryRepository.findByCategory(productDataRequest.getCategoryName())){
            this.productDataRepository.save(ProductData.builder()
                            .productId(String.valueOf(uuid))
                            .productName(productDataRequest.getProductName())
                            .productActualPrice(productDataRequest.getProductActualPrice())
                            .productSellingPrice(productDataRequest.getProductSellingPrice())
                            .addedOn(String.valueOf(new Date()))
                            .productDescription(productDataRequest.getProductDescription())
                            .stock(productDataRequest.getStock())
                            .rating(productDataRequest.getRating())
                            .categoryName(productDataRequest.getCategoryName())
                    .build());
            return new ApiResponse(false,"product added successfully");
        } else {
            return new ApiResponse(false,productDataRequest.getCategoryName()+" This category not found");
        }
    }
    public List<ProductDataRequest> getAllProduct() throws DataNotFoundException{
        List<ProductDataRequest> productDataRequests = new ArrayList<>();
        for (ProductData data: this.productDataRepository.findAll()) {
            productDataRequests.add(new ProductDataRequest(data.getProductName(), data.getProductActualPrice(), data.getProductSellingPrice(), data.getProductDescription(), data.getRating(), data.getStock(), data.getCategoryName()));
        }
        if (!productDataRequests.isEmpty()){
            return productDataRequests;
        } else {
            throw new DataNotFoundException("No Information Found");
        }
    }
    public List<ProductDataRequest> getProductByName(String name) throws DataNotFoundException{
        List<ProductDataRequest> productDataRequests = new ArrayList<>();
        for(ProductData data: this.productDataRepository.findAllByName(name)){
            productDataRequests.add(new ProductDataRequest(data.getProductName(), data.getProductActualPrice(), data.getProductSellingPrice(), data.getProductDescription(), data.getRating(), data.getStock(), data.getCategoryName()));
        }
        if (!productDataRequests.isEmpty()){
            return productDataRequests;
        } else {
            throw new DataNotFoundException("No Information Found");
        }
    }
}
