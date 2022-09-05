package com.ecommerce.app.debodelivery.service;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.entity.Category;
import com.ecommerce.app.debodelivery.entity.ProductData;
import com.ecommerce.app.debodelivery.entity.ProductImage;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.ProductDataRequest;
import com.ecommerce.app.debodelivery.model.ProductDataResponse;
import com.ecommerce.app.debodelivery.repository.CategoryRepository;
import com.ecommerce.app.debodelivery.repository.ProductDataRepository;
import com.ecommerce.app.debodelivery.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Service
public class ProductService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductDataRepository productDataRepository;
    @Autowired
    private ProductImageRepository productImageRepository;

    public ApiResponse addProduct(ProductDataRequest productDataRequest) throws FileNotFoundException, IOException {
        UUID uuid = UUID.randomUUID();
        UUID imageUuid = UUID.randomUUID();
        if (this.categoryRepository.findByCategory(productDataRequest.getCategoryName())) {
            File f = new File(productDataRequest.getProductImageUrl());
            byte[] bytes;
            String extension = "";
            if (f.exists()) {
                bytes = Files.readAllBytes(f.toPath());
                int i = f.getName().lastIndexOf('.');
                if (i > 0) {
                    extension = f.getName().substring(i + 1);
                }
            } else {
                throw new FileNotFoundException("File Not Found");
            }
            this.productDataRepository.save(ProductData.builder()
                    .productId(String.valueOf(uuid))
                    .productName(productDataRequest.getProductName())
                    .productActualPrice(productDataRequest.getProductActualPrice())
                    .discountSellingPrice(productDataRequest.getDiscountSellingPrice())
                    .productSellingPrice(productDataRequest.getProductSellingPrice())
                    .addedOn(new Date())
                    .productDescription(productDataRequest.getProductDescription())
                    .stock(productDataRequest.getStock())
                    .rating(productDataRequest.getRating())
                    .companyName(productDataRequest.getCompanyName())
                    .productImage(new ProductImage(String.valueOf(imageUuid), f.getName(), extension, bytes))
                    .category(categoryRepository.findByCategoryName(productDataRequest.getCategoryName()))
                    .build());
            return new ApiResponse(false, "product added successfully");
        } else {
            return new ApiResponse(false, productDataRequest.getCategoryName() + " This category not found");
        }
    }

    public List<ProductDataResponse> getAllProduct() throws DataNotFoundException {
        List<ProductDataResponse> productDataResponse = new ArrayList<>();
        for (ProductData data : this.productDataRepository.findAll()) {
            productDataResponse.add(new ProductDataResponse(data.getProductId(),data.getProductName(), data.getProductActualPrice(), data.getDiscountSellingPrice(), data.getProductSellingPrice(), data.getProductDescription(), data.getRating(), data.getStock(), data.getCategory().getCategoryName(), data.getCompanyName(), data.getProductImage().getImageId()));
        }
        if (!productDataResponse.isEmpty()) {
            return productDataResponse;
        } else {
            throw new DataNotFoundException("No Information Found");
        }
    }

    public List<ProductDataResponse> getProductByName(String name) throws DataNotFoundException {
        List<ProductDataResponse> productDataResponse = new ArrayList<>();
        for (ProductData data : this.productDataRepository.findAllByName(name)) {
            productDataResponse.add(new ProductDataResponse(data.getProductId(),data.getProductName(), data.getProductActualPrice(), data.getDiscountSellingPrice(), data.getProductSellingPrice(), data.getProductDescription(), data.getRating(), data.getStock(), data.getCategory().getCategoryName(), data.getCompanyName(), data.getProductImage().getImageId()));
        }
        if (!productDataResponse.isEmpty()) {
            return productDataResponse;
        } else {
            throw new DataNotFoundException("No Information Found");
        }
    }

    public List<ProductDataResponse> getProductByCategoryName(String name) throws DataNotFoundException {
        List<ProductDataResponse> productDataResponse = new ArrayList<>();
        Category category = this.categoryRepository.findByCategoryName(name);
        if (category != null) {
            for (ProductData data : this.productDataRepository.findAllByCategoryId(category)) {
                productDataResponse.add(new ProductDataResponse(data.getProductId(),data.getProductName(), data.getProductActualPrice(), data.getDiscountSellingPrice(), data.getProductSellingPrice(), data.getProductDescription(), data.getRating(), data.getStock(), data.getCategory().getCategoryName(), data.getCompanyName(), data.getProductImage().getImageId()));
            }
            if (!productDataResponse.isEmpty()) {
                return productDataResponse;
            } else {
                throw new DataNotFoundException("No Information Found");
            }
        } else {
            throw new DataNotFoundException("No Category Found");
        }
    }

    public List<ProductDataResponse> getProductByMaxPrice(Integer maxPrice) throws DataNotFoundException {
        List<ProductDataResponse> productDataResponse = new ArrayList<>();
        for (ProductData data : this.productDataRepository.findAllByMaxPrice(maxPrice)) {
            productDataResponse.add(new ProductDataResponse(data.getProductId(),data.getProductName(), data.getProductActualPrice(), data.getDiscountSellingPrice(), data.getProductSellingPrice(), data.getProductDescription(), data.getRating(), data.getStock(), data.getCategory().getCategoryName(), data.getCompanyName(), data.getProductImage().getImageId()));
        }
        if (!productDataResponse.isEmpty()) {
            return productDataResponse;
        } else {
            throw new DataNotFoundException("No Information Found");
        }
    }
    public List<ProductDataResponse> getProductByGivenRange(Integer minRange,Integer maxRange) throws DataNotFoundException {
        List<ProductDataResponse> productDataResponse = new ArrayList<>();
        for (ProductData data : this.productDataRepository.findAllByRange(minRange,maxRange)) {
            productDataResponse.add(new ProductDataResponse(data.getProductId(),data.getProductName(), data.getProductActualPrice(), data.getDiscountSellingPrice(), data.getProductSellingPrice(), data.getProductDescription(), data.getRating(), data.getStock(), data.getCategory().getCategoryName(), data.getCompanyName(), data.getProductImage().getImageId()));
        }
        if (!productDataResponse.isEmpty()) {
            return productDataResponse;
        } else {
            throw new DataNotFoundException("No Information Found");
        }
    }
    public byte[] imageData(String imageId) throws FileNotFoundException{
        Optional<ProductImage> data = this.productImageRepository.findById(imageId);
        if (data.isPresent()){
            return data.get().getImageData();
        } else {
            throw new FileNotFoundException("Image Not Found");
        }
    }
}
