package com.stripe.stripepayments.commons.enums;

public enum StripeEventsEnum {
    PAYMENT_INTENT_SUCCEED("payment_intent.succeeded"),
    CHECK0UT_SESSION_COMPLETED("checkout.session.completed");

    public final String value;

    StripeEventsEnum(String value){
        this.value = value;
    }
}
