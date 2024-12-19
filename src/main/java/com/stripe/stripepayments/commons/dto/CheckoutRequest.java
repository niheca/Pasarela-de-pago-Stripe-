package com.stripe.stripepayments.commons.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequest {
    @NonNull
    private String customerId;
    @NonNull
    private String productId;
}
