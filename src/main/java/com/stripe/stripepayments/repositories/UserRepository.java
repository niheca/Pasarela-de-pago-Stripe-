package com.stripe.stripepayments.repositories;

import com.stripe.stripepayments.commons.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
