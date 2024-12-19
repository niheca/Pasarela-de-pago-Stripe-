package com.stripe.stripepayments.strategy.impl;

import com.stripe.model.Event;
//import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentIntent;
import com.stripe.stripepayments.commons.entities.Payment;
import com.stripe.stripepayments.commons.enums.StripeEventsEnum;
import com.stripe.stripepayments.repositories.PaymentRepository;
import com.stripe.stripepayments.strategy.StripeStrategy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StripeStrategyPaymentIntentSucceed implements StripeStrategy {

    private final PaymentRepository paymentRepository;

    public StripeStrategyPaymentIntentSucceed(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @Override
    public boolean isApplicable(Event event) {
        return StripeEventsEnum.PAYMENT_INTENT_SUCCEED.value.equals(event.getType());
    }

    @Override
    public Event process(Event event) {
        return Optional.of(event)
                .map(this::deserialize)
                .map(this::mapToEntity)
                .map(paymentRepository::save)
                .map(given ->event)
                .orElseThrow(() -> new RuntimeException("Error proccesing Payment Intent"));
    }

    private Payment mapToEntity(PaymentIntent paymentIntent) {
       try {
           return Payment.builder()
                   .paymentIntentId(paymentIntent.getId())
                   .customerId(paymentIntent.getCustomer())
                   .amount(paymentIntent.getAmount())
                   .currency(paymentIntent.getCurrency())
                   .type(StripeEventsEnum.PAYMENT_INTENT_SUCCEED)
                   .build();
       }catch (Exception e) {
           System.out.println("Error MapToEntity");
           return null;
       }
    }

    private PaymentIntent deserialize(Event event) {
        return (PaymentIntent) event.getDataObjectDeserializer().getObject()
                .orElseThrow(() -> new RuntimeException("Error deserializing Payment"));
    }
}
