package com.stripe.stripepayments.controllers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.model.Event;
import com.stripe.stripepayments.commons.dto.CheckoutRequest;
import com.stripe.stripepayments.commons.dto.CheckoutResponse;
import com.stripe.stripepayments.controllers.StripeApi;
import com.stripe.stripepayments.services.impl.StripeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StripeController implements StripeApi {

    private final StripeServiceImpl stripeService;
    private final StripeServiceImpl stripeServiceImpl;

    public StripeController(StripeServiceImpl stripeService, StripeServiceImpl stripeServiceImpl) {
        this.stripeService = stripeService;
        this.stripeServiceImpl = stripeServiceImpl;
    }

    @Override
    public ResponseEntity<Void> stripeWebhook(String payload, String stripeHeader) {
        Event event = stripeService.construcEvent(payload, stripeHeader);
        stripeServiceImpl.manageWebhook(event);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CheckoutResponse> createCheckout(CheckoutRequest checkoutRequest) {
        return ResponseEntity.ok(stripeService.createCheckout(checkoutRequest));
    }
}
