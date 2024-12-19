package com.stripe.stripepayments.controllers;

import com.stripe.stripepayments.commons.constants.ApiPathConstants;
import com.stripe.stripepayments.commons.dto.CheckoutRequest;
import com.stripe.stripepayments.commons.dto.CheckoutResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.STRIPE_ROOT)
public interface StripeApi {

    @PostMapping(value = "/webhook",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> stripeWebhook(@RequestBody String payload,
                                       @RequestHeader("Stripe-Signature") String stripeHeader);

    @PostMapping(value = "/checkout")
    ResponseEntity<CheckoutResponse> createCheckout(@RequestBody @Valid CheckoutRequest checkoutRequest)
}
