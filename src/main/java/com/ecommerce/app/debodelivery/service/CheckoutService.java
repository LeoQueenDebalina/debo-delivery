package com.ecommerce.app.debodelivery.service;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.entity.AddToCart;
import com.ecommerce.app.debodelivery.entity.OrderCancelDetails;
import com.ecommerce.app.debodelivery.entity.OrderFeedback;
import com.ecommerce.app.debodelivery.model.*;
import com.ecommerce.app.debodelivery.helper.OrderStatus;
import com.ecommerce.app.debodelivery.entity.Checkout;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CheckoutService {
    @Autowired
    private CheckOutRepository checkOutRepository;
    @Autowired
    private AddToCartRepository addToCartRepository;
    @Autowired
    private ProductDataRepository productDataRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;
    @Autowired
    private OrderCancelDetailsRepository orderCancelDetailsRepository;
    @Autowired
    private OrderFeedbackRepository orderFeedbackRepository;

    public ApiResponse buyProduct(OrderedProductRequest orderedProductRequest) {
        UUID uuid = UUID.randomUUID();
        if (userRepository.ifNumberIsExist(orderedProductRequest.getUserMobileNumber())) {
            if (productDataRepository.existsById(orderedProductRequest.getProductId())) {
                if (deliveryAddressRepository.ifAddressAlreadyExist(userRepository.findByMobileNumber(orderedProductRequest.getUserMobileNumber()))) {
                    if (productDataRepository.findProductById(orderedProductRequest.getProductId()).getStock() != 0) {
                        this.checkOutRepository.save(Checkout.builder()
                                .checkoutId(String.valueOf(uuid))
                                .paymentType(orderedProductRequest.getPaymentType())
                                .deliveryAddress(deliveryAddressRepository.findAddress(userRepository.findByMobileNumber(orderedProductRequest.getUserMobileNumber())))
                                .user(userRepository.findByMobileNumber(orderedProductRequest.getUserMobileNumber()))
                                .productData(productDataRepository.findProductById(orderedProductRequest.getProductId()))
                                .orderStatus(OrderStatus.ORDERED)
                                .orderDate(new Date())
                                .deliveryDate(new Date(new Date().getTime() + (new Random().nextInt(4) + 6) * 24 * 60 * 60 * 1000))
                                .deliveryStatus(false)
                                .cancelLastDate(null)
                                .cancelBlockStatus(false)
                                .cancelDate(null)
                                .cancelStatus(false)
                                .receivedDate(null)
                                .receivedStatus(false)
                                .build());
                        this.productDataRepository.removeStock(orderedProductRequest.getProductId());
                        return new ApiResponse(false, "you successfully placed the order");
                    } else {
                        return new ApiResponse(true, "This product is out of stock");
                    }
                } else {
                    return new ApiResponse(true, "Address Not Found");
                }
            } else {
                return new ApiResponse(true, "Product Not Found");
            }
        } else {
            return new ApiResponse(true, "User Not Found");
        }
    }

    public List<OrderedProductResponse> getOrderedProduct(String mobileNumber) throws DataNotFoundException {
        if (userRepository.ifNumberIsExist(mobileNumber)) {
            List<OrderedProductResponse> rData = new ArrayList<>();
            for (Checkout data : this.checkOutRepository.findOrderByUser(userRepository.findByMobileNumber(mobileNumber))) {
                rData.add(new OrderedProductResponse(data.getCheckoutId(), data.getPaymentType(), data.getDeliveryAddress().getDeliveredAddressId(), data.getProductData().getProductId(), data.getOrderStatus(), data.getOrderDate(), data.getDeliveryDate(), data.getDeliveryStatus(), data.getCancelDate(), data.getCancelStatus(), data.getReceivedDate()));
            }
            if (rData.isEmpty()) {
                throw new DataNotFoundException("Order Item Not Found");
            }
            return rData;
        } else {
            throw new DataNotFoundException("User Not Found");
        }
    }

    public ApiResponse cancelOrder(OrderCancelRequest orderCancelRequest) {
        if (userRepository.ifNumberIsExist(orderCancelRequest.getUserMobileNumber())) {
            if (checkOutRepository.orderedProductIsExist(userRepository.findByMobileNumber(orderCancelRequest.getUserMobileNumber()), orderCancelRequest.getCheckoutId())) {
                Optional<Checkout> oldData = this.checkOutRepository.findById(orderCancelRequest.getCheckoutId());
                if (!oldData.get().getCancelStatus()) {
                    if (oldData.get().getCancelBlockStatus()) {
                        Checkout newData = new Checkout();
                        newData.setCheckoutId(oldData.get().getCheckoutId());
                        newData.setPaymentType(oldData.get().getPaymentType());
                        newData.setDeliveryAddress(oldData.get().getDeliveryAddress());
                        newData.setUser(oldData.get().getUser());
                        newData.setProductData(oldData.get().getProductData());
                        newData.setOrderStatus(OrderStatus.CANCELLED);
                        newData.setOrderDate(oldData.get().getOrderDate());
                        if (oldData.get().getDeliveryStatus()) {
                            newData.setDeliveryDate(oldData.get().getDeliveryDate());
                            newData.setDeliveryStatus(oldData.get().getDeliveryStatus());

                            newData.setReceivedDate(new Date(new Date().getTime() + (new Random().nextInt(4) + 6) * 24 * 60 * 60 * 1000));
                            newData.setReceivedStatus(true);
                        } else {
                            newData.setDeliveryDate(null);
                            newData.setDeliveryStatus(false);

                            newData.setReceivedDate(oldData.get().getReceivedDate());
                            newData.setReceivedStatus(oldData.get().getReceivedStatus());

                            this.productDataRepository.addStock(oldData.get().getProductData().getProductId());
                        }
                        newData.setReceivedDate(null);
                        newData.setReceivedStatus(false);
                        newData.setCancelDate(new Date());
                        newData.setCancelStatus(true);
                        this.checkOutRepository.save(newData);
                        if (orderCancelRequest.getFeedbackMessage() != "") {
                            UUID uuid = UUID.randomUUID();
                            this.orderCancelDetailsRepository.save(OrderCancelDetails.builder()
                                    .cancelId(String.valueOf(uuid))
                                    .checkout(oldData.get())
                                    .cancelReason(orderCancelRequest.getFeedbackMessage())
                                    .date(new Date())
                                    .build());
                        }
                        return new ApiResponse(false, "cancel successfully");
                    } else {
                        return new ApiResponse(true, "cancellation date expire");
                    }
                } else {
                    return new ApiResponse(true, "You have already cancelled the order");
                }
            } else {
                return new ApiResponse(true, "order item not found");
            }
        } else {
            return new ApiResponse(true, "user not found");
        }
    }

    public ApiResponse OrderProductFeedBack(OrderProductFeedBackRequest orderProductFeedBackRequest) {
        if (userRepository.ifNumberIsExist(orderProductFeedBackRequest.getUserMobileNumber())) {
            if (checkOutRepository.orderedProductIsExist(userRepository.findByMobileNumber(orderProductFeedBackRequest.getUserMobileNumber()), orderProductFeedBackRequest.getCheckoutId())) {
                Optional<Checkout> oldData = this.checkOutRepository.findById(orderProductFeedBackRequest.getCheckoutId());
                if (!oldData.get().getCancelStatus()) {
                    if (oldData.get().getDeliveryStatus()) {
                        if (orderProductFeedBackRequest.getFeedbackMessage() != "") {
                            UUID uuid = UUID.randomUUID();
                            this.orderFeedbackRepository.save(OrderFeedback.builder()
                                    .feedBackId(String.valueOf(uuid))
                                    .checkout(oldData.get())
                                    .feedBackMessage(orderProductFeedBackRequest.getFeedbackMessage())
                                    .date(new Date())
                                    .build());
                            return new ApiResponse(false, "Thank You For Give FeedBack");
                        } else {
                            return new ApiResponse(true, "Message not found");
                        }
                    } else {
                        return new ApiResponse(true, "Product Not Delivered");
                    }
                } else {
                    return new ApiResponse(true, "You have already cancelled the order");
                }
            } else {
                return new ApiResponse(true, "Order item not found");
            }
        } else {
            return new ApiResponse(true, "User not found");
        }
    }

    public ApiResponse orderProductFromCart(CartProductOrderedRequest cartProductOrderedRequest) {
        if (userRepository.ifNumberIsExist(cartProductOrderedRequest.getUserMobileNumber())) {
            if (addToCartRepository.isExistCartCheckByUser(userRepository.findByMobileNumber(cartProductOrderedRequest.getUserMobileNumber()))) {
                if (deliveryAddressRepository.ifAddressAlreadyExist(userRepository.findByMobileNumber(cartProductOrderedRequest.getUserMobileNumber()))) {
                    for (AddToCart data : this.addToCartRepository.selectAllCartDataByUser(userRepository.findByMobileNumber(cartProductOrderedRequest.getUserMobileNumber()))) {
                        UUID uuid = UUID.randomUUID();
                        if (productDataRepository.existsById(data.getProductData().getProductId()) && productDataRepository.findProductById(data.getProductData().getProductId()).getStock() != 0) {
                            this.checkOutRepository.save(Checkout.builder()
                                    .checkoutId(String.valueOf(uuid))
                                    .paymentType(cartProductOrderedRequest.getPaymentType())
                                    .deliveryAddress(deliveryAddressRepository.findAddress(userRepository.findByMobileNumber(cartProductOrderedRequest.getUserMobileNumber())))
                                    .user(userRepository.findByMobileNumber(cartProductOrderedRequest.getUserMobileNumber()))
                                    .productData(productDataRepository.findProductById(data.getProductData().getProductId()))
                                    .orderStatus(OrderStatus.ORDERED)
                                    .orderDate(new Date())
                                    .deliveryDate(new Date(new Date().getTime() + (new Random().nextInt(4) + 6) * 24 * 60 * 60 * 1000))
                                    .deliveryStatus(false)
                                    .cancelLastDate(null)
                                    .cancelBlockStatus(false)
                                    .cancelDate(null)
                                    .cancelStatus(false)
                                    .receivedDate(null)
                                    .receivedStatus(false)
                                    .build());
                            this.productDataRepository.removeStock(data.getProductData().getProductId());
                        }
                    }
                    return new ApiResponse(false, "You successfully placed the order");
                } else {
                    return new ApiResponse(false, "Address Not Found");
                }
            } else {
                return new ApiResponse(true, "Cart item not found");
            }
        } else {
            return new ApiResponse(true, "User not found");
        }
    }
}
