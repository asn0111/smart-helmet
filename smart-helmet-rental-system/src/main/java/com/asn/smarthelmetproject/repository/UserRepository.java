package com.asn.smarthelmetproject.repository;

import com.asn.smarthelmetproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
