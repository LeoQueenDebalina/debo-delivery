package com.ecommerce.app.debodelivery.service;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.entity.AddToCart;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.TotalPriceInCartResponse;
import com.ecommerce.app.debodelivery.model.ViewCartResponse;
import com.ecommerce.app.debodelivery.repository.AddToCartRepository;
import com.ecommerce.app.debodelivery.repository.ProductDataRepository;
import com.ecommerce.app.debodelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {
    @Autowired
    private AddToCartRepository addToCartRepository;
    @Autowired
    private ProductDataRepository productDataRepository;
    @Autowired
    private UserRepository userRepository;

    public ApiResponse addProductToCart(String userMobileNumber, String productId) {
        UUID uuid = UUID.randomUUID();
        if (userRepository.ifNumberIsExist(userMobileNumber)) {
            if (productDataRepository.existsById(productId)) {
                if (!addToCartRepository.isExistCart(productDataRepository.findProductById(productId), userRepository.findByMobileNumber(userMobileNumber))) {
                    this.addToCartRepository.save(AddToCart.builder()
                            .cartId(String.valueOf(uuid))
                            .productData(productDataRepository.findProductById(productId))
                            .user(userRepository.findByMobileNumber(userMobileNumber))
                            .addedDate(new Date())
                            .build());
                    return new ApiResponse(false, "Added to cart");
                } else {
                    return new ApiResponse(true, "product is already added to cart");
                }
            } else {
                return new ApiResponse(true, "Product not found");
            }
        } else {
            return new ApiResponse(true, "User not found");
        }
    }

    public ApiResponse deleteProductFromCart(String userMobileNumber, String productId) {
        UUID uuid = UUID.randomUUID();
        if (userRepository.ifNumberIsExist(userMobileNumber)) {
            if (productDataRepository.existsById(productId)) {
                if (addToCartRepository.isExistCart(productDataRepository.findProductById(productId), userRepository.findByMobileNumber(userMobileNumber))) {
                    this.addToCartRepository.delete(addToCartRepository.selectByProductData(productDataRepository.findProductById(productId), userRepository.findByMobileNumber(userMobileNumber)));
                    return new ApiResponse(false, "Product remove from cart");
                } else {
                    return new ApiResponse(true, "product not added to cart");
                }
            } else {
                return new ApiResponse(true, "Product not found");
            }
        } else {
            return new ApiResponse(true, "User not found");
        }
    }
    public List<ViewCartResponse> viewCart(String userMobileNumber) throws DataNotFoundException{
        if (userRepository.ifNumberIsExist(userMobileNumber)) {
            List<ViewCartResponse> rData= new ArrayList<>();
            for (AddToCart data : this.addToCartRepository.selectByAllCartData(userRepository.findByMobileNumber(userMobileNumber))){
                rData.add(new ViewCartResponse(
                        data.getProductData().getProductId(),
                        data.getProductData().getProductName(),
                        data.getProductData().getProductSellingPrice(),
                        data.getCartId()));
            }
            if (rData.isEmpty()){
                throw new DataNotFoundException("No Cart Item found");
            }
            return rData;
        } else {
            throw new DataNotFoundException("User Not Found");
        }
    }
    public TotalPriceInCartResponse totalPriceInCart(String userPhoneNumber) throws DataNotFoundException{
        Integer productActualPrice=0;
        Integer discountSellingPrice=0;
        Integer productSellingPrice=0;
        if (userRepository.ifNumberIsExist(userPhoneNumber)) {
            for (AddToCart data : this.addToCartRepository.selectByAllCartData(userRepository.findByMobileNumber(userPhoneNumber))){
                productActualPrice+= data.getProductData().getProductActualPrice();
                discountSellingPrice+= data.getProductData().getDiscountSellingPrice();
                productSellingPrice+= data.getProductData().getProductSellingPrice();
            }
            if (productActualPrice!=0){
                return new TotalPriceInCartResponse(productActualPrice,discountSellingPrice,productSellingPrice);
            } else {
                return new TotalPriceInCartResponse();
            }
        } else {
            throw new DataNotFoundException("User Not Found");
        }
    }

}
