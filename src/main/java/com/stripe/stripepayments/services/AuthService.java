package com.stripe.stripepayments.services;

import com.stripe.stripepayments.commons.dto.AuthResponse;
import com.stripe.stripepayments.commons.dto.UserRequest;

public interface AuthService {

    AuthResponse createUser(UserRequest userRequest);

}
