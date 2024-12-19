package com.stripe.stripepayments.repositories;

import com.stripe.stripepayments.commons.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByPaymentIntentId(String paymentId);
}
