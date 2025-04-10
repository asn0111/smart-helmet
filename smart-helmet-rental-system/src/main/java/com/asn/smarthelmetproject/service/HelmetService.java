package com.asn.smarthelmetproject.service;

import com.asn.smarthelmetproject.model.Helmet;
import com.asn.smarthelmetproject.repository.HelmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelmetService {

    @Autowired
    private HelmetRepository helmetRepository;

    public boolean isHelmetAvailable(String rfidTag) {
        Helmet helmet = helmetRepository.findByRfidTag(rfidTag);
        return helmet != null && helmet.isAvailable();
    }

    public void updateHelmetAvailability(String rfidTag, boolean availability) {
        Helmet helmet = helmetRepository.findByRfidTag(rfidTag);
        if (helmet != null) {
            helmet.setAvailable(availability);
            helmetRepository.save(helmet);
        }
    }
}

