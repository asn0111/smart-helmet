package com.asn.smarthelmetproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.asn.smarthelmetproject.service.HelmetService;
import com.asn.smarthelmetproject.utils.ESP32Client;

@RestController
@RequestMapping("/api/helmets")
public class HelmetController {

    @Autowired
    private HelmetService helmetService;

    @Autowired
    private ESP32Client esp32Client;

    @PostMapping("/unlock/{rfidTag}")
    public String unlockHelmet(@PathVariable String rfidTag) {
        if (helmetService.isHelmetAvailable(rfidTag)) {
            esp32Client.sendUnlockRequest(rfidTag);
            helmetService.updateHelmetAvailability(rfidTag, false);
            return "Helmet unlocked!";
        } else {
            return "Helmet is not available!";
        }
    }

    @PostMapping("/lock/{rfidTag}")
    public String lockHelmet(@PathVariable String rfidTag) {
        esp32Client.sendLockRequest(rfidTag);
        helmetService.updateHelmetAvailability(rfidTag, true);
        return "Helmet locked and returned!";
    }
}
