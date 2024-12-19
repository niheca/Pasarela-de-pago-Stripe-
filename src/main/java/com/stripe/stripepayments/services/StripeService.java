package com.stripe.stripepayments.services;

import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.stripepayments.commons.dto.CheckoutRequest;
import com.stripe.stripepayments.commons.dto.CheckoutResponse;

public interface StripeService {

    void manageWebhook(Event event);

    Event construcEvent(String payload,String stripeHeader);

    Customer createCustomer(String email);

    Product createProduct(String name);

    Price createPrice(String productId);

    CheckoutResponse createCheckout(CheckoutRequest checkoutRequest);
}
