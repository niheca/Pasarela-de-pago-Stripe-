package com.stripe.stripepayments.strategy.impl;

import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.stripepayments.commons.entities.Payment;
import com.stripe.stripepayments.commons.enums.StripeEventsEnum;
import com.stripe.stripepayments.repositories.PaymentRepository;
import com.stripe.stripepayments.strategy.StripeStrategy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StripeStrategyCheckoutSessionCompleted  implements StripeStrategy {

    private final PaymentRepository paymentRepository;

    public StripeStrategyCheckoutSessionCompleted(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @Override
    public boolean isApplicable(Event event) {
        return StripeEventsEnum.CHECK0UT_SESSION_COMPLETED.value.equals(event.getType());
    }

    @Override
    public Event process(Event event) {
        var session = this.deserialize(event);
        return Optional.of(event)
                .map(givenEvent -> paymentRepository.findByPaymentIntentId(session.getPaymentIntent()))
                .map(payment -> setProductId(payment,session.getMetadata().get("product_id")))
                .map(given -> event)
                .orElseThrow(() -> new RuntimeException("Error processing event"));
    }

    private Payment setProductId(Payment payment, String productId) {
        payment.setProductId(productId);
        payment.setType(StripeEventsEnum.CHECK0UT_SESSION_COMPLETED);
        return payment;
    }

    private Session deserialize (Event event) {
        return (Session) event.getDataObjectDeserializer().getObject()
                .orElseThrow(() -> new RuntimeException("Error deserializing Session"));
    }
}
