package com.asn.smarthelmetproject.repository;

import com.asn.smarthelmetproject.model.Helmet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelmetRepository extends JpaRepository<Helmet, Long> {
    Helmet findByRfidTag(String rfidTag);
}
