package com.asn.project;

public class RFIDScanner {
    private static final String VALID_RFID_TAG = "123456ABC"; // Simulated valid RFID tag

    public String scanRFID() {
        System.out.println("Scanning RFID tag...");

        try {
            Thread.sleep(2000); // Simulate scanning delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("RFID Tag Detected: " + VALID_RFID_TAG);
        return VALID_RFID_TAG;
    }

    // ✅ Add this method to check if the scanned RFID is valid
    public boolean isValidRFID(String scannedTag) {
        return VALID_RFID_TAG.equals(scannedTag);
    }
}
