package com.asn.project;

public class LockManager {
    private ESP32Client esp32Client;
    private RFIDScanner rfidScanner;
    private WeightSensor weightSensor;

    public LockManager(ESP32Client esp32Client, RFIDScanner rfidScanner, WeightSensor weightSensor) {
        this.esp32Client = esp32Client;
        this.rfidScanner = rfidScanner;
        this.weightSensor = weightSensor;
    }

    public void unlockHelmet(String unlockCode) {
        esp32Client.sendUnlockRequest(unlockCode);
    }

    public boolean lockHelmet(String returnCode, String scannedRFID, double detectedWeight) {
        if (rfidScanner.isValidRFID(scannedRFID) && weightSensor.isHelmetPlaced(detectedWeight)) {
            esp32Client.sendLockRequest(returnCode);
            return true;
        } else {
            System.out.println("Helmet not placed correctly. Please ensure it's inside the box.");
            return false;
        }
    }
}
