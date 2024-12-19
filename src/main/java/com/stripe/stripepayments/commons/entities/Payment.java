package com.stripe.stripepayments.commons.entities;

import com.stripe.stripepayments.commons.enums.StripeEventsEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Bag;

@Entity
@Table(name = "payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String paymentIntentId;

    private String customerId;

    private String productId;

    private Long amount;

    private String currency;

    @Enumerated(EnumType.STRING)
    private StripeEventsEnum type;

}
