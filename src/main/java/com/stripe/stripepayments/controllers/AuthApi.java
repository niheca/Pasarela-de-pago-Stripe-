package com.stripe.stripepayments.controllers;

import com.stripe.stripepayments.commons.constants.ApiPathConstants;
import com.stripe.stripepayments.commons.dto.AuthResponse;
import com.stripe.stripepayments.commons.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.AUTH_ROOT)
public interface AuthApi {
    @PostMapping()
    ResponseEntity<AuthResponse> createUser(@RequestBody @Valid UserRequest userRequest);
}
