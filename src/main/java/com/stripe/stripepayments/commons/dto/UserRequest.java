package com.stripe.stripepayments.commons.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NonNull @Email
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String name;
}
