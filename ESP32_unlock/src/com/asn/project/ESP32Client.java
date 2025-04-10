package com.asn.project;

import java.net.HttpURLConnection;
import java.net.URL;

public class ESP32Client {
    private static final String ESP32_IP = "http://192.168.X.X"; // Replace with actual IP

    public void sendUnlockRequest(String unlockCode) {
        sendRequest("/unlock?code=" + unlockCode);
    }

    public void sendLockRequest(String unlockCode) {
        sendRequest("/lock?code=" + unlockCode);
    }

    private void sendRequest(String endpoint) {
        try {
            URL url = new URL(ESP32_IP + endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Success: " + endpoint + " executed");
            } else {
                System.out.println("Failed: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
