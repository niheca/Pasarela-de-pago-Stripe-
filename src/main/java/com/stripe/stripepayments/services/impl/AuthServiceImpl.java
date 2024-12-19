package com.stripe.stripepayments.services.impl;

import com.stripe.stripepayments.commons.dto.AuthResponse;
import com.stripe.stripepayments.commons.dto.UserRequest;
import com.stripe.stripepayments.commons.entities.UserModel;
import com.stripe.stripepayments.repositories.UserRepository;
import com.stripe.stripepayments.services.AuthService;
import com.stripe.stripepayments.services.StripeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final StripeService stripeService;

    public AuthServiceImpl(UserRepository userRepository, StripeService stripeService) {
        this.userRepository = userRepository;
        this.stripeService = stripeService;
    }

    @Override
    public AuthResponse createUser(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(this::setUserCustomerAndProduct)
                .map(userRepository::save)
                .map(userModel -> AuthResponse.builder()
                        .customerId(userModel.getCustomerId())
                        .productId(userModel.getProductId())
                        .build())
                .orElseThrow(() -> new RuntimeException("Error creting user"));
    }

    private UserModel setUserCustomerAndProduct(UserModel userModel) {
        var customerCreated = stripeService.createCustomer(userModel.getEmail());
        var productCreated = stripeService.createProduct(userModel.getName());
        stripeService.createPrice(productCreated.getId());

        userModel.setProductId(productCreated.getId());
        userModel.setCustomerId(customerCreated.getId());

        return userModel;
    }

    private UserModel mapToEntity(UserRequest userRequest) {
        return UserModel.builder()
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .build();
    }
}
