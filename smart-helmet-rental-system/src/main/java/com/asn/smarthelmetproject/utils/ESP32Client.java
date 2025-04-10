package com.asn.smarthelmetproject.utils;

import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class ESP32Client {

    private static final String ESP32_IP = "http://192.168.X.X"; // Replace with actual IP

    public void sendUnlockRequest(String rfidTag) {
        sendRequest("/unlock?rfid=" + rfidTag);
    }

    public void sendLockRequest(String rfidTag) {
        sendRequest("/lock?rfid=" + rfidTag);
    }

    private void sendRequest(String endpoint) {
        try {
            URL url = new URL(ESP32_IP + endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                System.out.println("ESP32: " + endpoint + " executed successfully");
            } else {
                System.out.println("ESP32 Error: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
