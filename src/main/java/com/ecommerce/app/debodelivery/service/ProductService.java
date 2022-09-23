package com.ecommerce.app.debodelivery.service;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.entity.Category;
import com.ecommerce.app.debodelivery.entity.ProductData;
import com.ecommerce.app.debodelivery.entity.ProductImage;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.PageOfProductDataResponse;
import com.ecommerce.app.debodelivery.model.ProductDataRequest;
import com.ecommerce.app.debodelivery.model.ProductDataResponse;
import com.ecommerce.app.debodelivery.repository.CategoryRepository;
import com.ecommerce.app.debodelivery.repository.ProductDataRepository;
import com.ecommerce.app.debodelivery.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductDataRepository productDataRepository;
    @Autowired
    private ProductImageRepository productImageRepository;

    public ApiResponse addProduct(ProductDataRequest productDataRequest) throws FileNotFoundException, IOException {
        if (this.categoryRepository.existsCategoryByCategoryNameContaining(productDataRequest.getCategoryName())) {
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
                    .productId(UUID.randomUUID().toString())
                    .productName(productDataRequest.getProductName())
                    .productActualPrice(productDataRequest.getProductActualPrice())
                    .discountSellingPrice(productDataRequest.getDiscountSellingPrice())
                    .productSellingPrice(productDataRequest.getProductActualPrice() - productDataRequest.getDiscountSellingPrice())
                    .addedOn(new Date())
                    .productDescription(productDataRequest.getProductDescription())
                    .stock(productDataRequest.getStock())
                    .rating(productDataRequest.getRating())
                    .companyName(productDataRequest.getCompanyName())
                    .productImage(new ProductImage(UUID.randomUUID().toString(), f.getName(), extension, bytes))
                    .category(categoryRepository.findByCategoryNameContaining(productDataRequest.getCategoryName()))
                    .build());
            return new ApiResponse(false, "Product added successfully");
        } else {
            return new ApiResponse(false, productDataRequest.getCategoryName() + " This category not found");
        }
    }

    public List<ProductDataResponse> getAllProduct() throws DataNotFoundException {
        List<ProductDataResponse> productDataResponse = new ArrayList<>();
        for (ProductData data : this.productDataRepository.findAll()) {
            productDataResponse.add(new ProductDataResponse(data.getProductId(),
                    data.getProductName(),
                    data.getProductActualPrice(),
                    data.getDiscountSellingPrice(),
                    data.getProductSellingPrice(),
                    data.getProductDescription(),
                    data.getRating(),
                    data.getStock(),
                    data.getCategory().getCategoryName(),
                    data.getCompanyName(),
                    data.getProductImage().getImageId()));
        }
        if (!productDataResponse.isEmpty()) {
            return productDataResponse;
        } else {
            throw new DataNotFoundException("No Information Found");
        }
    }

    public List<ProductDataResponse> getProductByName(String name) throws DataNotFoundException {
        List<ProductDataResponse> productDataResponse = new ArrayList<>();
        for (ProductData data : this.productDataRepository.findByProductNameContaining(name)) {
            productDataResponse.add(new ProductDataResponse(data.getProductId(),
                    data.getProductName(),
                    data.getProductActualPrice(),
                    data.getDiscountSellingPrice(),
                    data.getProductSellingPrice(),
                    data.getProductDescription(),
                    data.getRating(),
                    data.getStock(),
                    data.getCategory().getCategoryName(),
                    data.getCompanyName(),
                    data.getProductImage().getImageId()));
        }
        if (!productDataResponse.isEmpty()) {
            return productDataResponse;
        } else {
            throw new DataNotFoundException("No Information Found");
        }
    }

    public List<ProductDataResponse> getProductByCategoryName(String name) throws DataNotFoundException {
        List<ProductDataResponse> productDataResponse = new ArrayList<>();
        Category category = this.categoryRepository.findByCategoryNameContaining(name);
        if (category != null) {
            for (ProductData data : this.productDataRepository.findByCategory(category)) {
                productDataResponse.add(new ProductDataResponse(data.getProductId(),
                        data.getProductName(),
                        data.getProductActualPrice(),
                        data.getDiscountSellingPrice(),
                        data.getProductSellingPrice(),
                        data.getProductDescription(),
                        data.getRating(),
                        data.getStock(),
                        data.getCategory().getCategoryName(),
                        data.getCompanyName(),
                        data.getProductImage().getImageId()));
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
        for (ProductData data : this.productDataRepository.findByProductSellingPriceLessThanEqual(maxPrice)) {
            productDataResponse.add(new ProductDataResponse(data.getProductId(),
                    data.getProductName(),
                    data.getProductActualPrice(),
                    data.getDiscountSellingPrice(),
                    data.getProductSellingPrice(),
                    data.getProductDescription(),
                    data.getRating(),
                    data.getStock(),
                    data.getCategory().getCategoryName(),
                    data.getCompanyName(),
                    data.getProductImage().getImageId()));
        }
        if (!productDataResponse.isEmpty()) {
            return productDataResponse;
        } else {
            throw new DataNotFoundException("No Information Found");
        }
    }

    public PageOfProductDataResponse getProductByGivenRange(Integer page, Integer size) throws DataNotFoundException {
        Page<ProductData> productDataPage = this.productDataRepository.findAll(PageRequest.of(page, size));
        List<ProductDataResponse> productDataResponse = productDataPage.getContent()
                .stream().map(data -> new ProductDataResponse(data.getProductId(),
                        data.getProductName(),
                        data.getProductActualPrice(),
                        data.getDiscountSellingPrice(),
                        data.getProductSellingPrice(),
                        data.getProductDescription(),
                        data.getRating(),
                        data.getStock(),
                        data.getCategory().getCategoryName(),
                        data.getCompanyName(),
                        data.getProductImage().getImageId())).collect(Collectors.toList());
        if (productDataResponse.isEmpty()) {
            throw new DataNotFoundException("No Information Found");
        } else {
            return new PageOfProductDataResponse(productDataResponse, productDataPage.getNumber(), productDataPage.getSize(), productDataPage.getTotalElements(), productDataPage.getTotalPages(), productDataPage.isLast());
        }
    }

    public byte[] imageData(String imageId) throws FileNotFoundException {
        Optional<ProductImage> data = this.productImageRepository.findById(imageId);
        if (data.isPresent()) {
            return data.get().getImageData();
        } else {
            throw new FileNotFoundException("Image Not Found");
        }
    }

    public List<ProductDataResponse> findProductsWithSorting(String field) throws DataNotFoundException {
        List<ProductDataResponse> productDataResponse = new ArrayList<>();
        for (ProductData data : this.productDataRepository.findAll(Sort.by(Sort.Direction.ASC, field))) {
            productDataResponse.add(new ProductDataResponse(data.getProductId(),
                    data.getProductName(),
                    data.getProductActualPrice(),
                    data.getDiscountSellingPrice(),
                    data.getProductSellingPrice(),
                    data.getProductDescription(),
                    data.getRating(),
                    data.getStock(),
                    data.getCategory().getCategoryName(),
                    data.getCompanyName(),
                    data.getProductImage().getImageId()));
        }
        if (!productDataResponse.isEmpty()) {
            return productDataResponse;
        } else {
            throw new DataNotFoundException("No Information Found");
        }
    }
}
