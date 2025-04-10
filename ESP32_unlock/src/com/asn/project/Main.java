package com.asn.project;

import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ESP32Client esp32Client = new ESP32Client();
        RFIDScanner rfidScanner = new RFIDScanner();
        WeightSensor weightSensor = new WeightSensor();
        LockManager lockManager = new LockManager(esp32Client, rfidScanner, weightSensor);
        Wallet wallet = new Wallet(50.0); // User starts with ₹50 in the wallet

        // ✅ Step 1: Generate and display unlock code
        String unlockCode = CodeGenerator.generateCode();
        System.out.println("Generated Code: " + unlockCode);

        System.out.println("Scan the QR code or enter the code to unlock the helmet: ");
        String enteredCode = scanner.nextLine();

        if (!enteredCode.equals(unlockCode)) {
            System.out.println("Invalid Code. Please try again.");
            return;
        }

        // ✅ Step 2: Check wallet balance before unlocking
        double pricePerMinute = 2.0;
        if (wallet.getBalance() < pricePerMinute) {
            System.out.println("Insufficient balance. Please recharge your wallet.");
            return;
        }

        lockManager.unlockHelmet(unlockCode);
        System.out.println("Helmet Unlocked! Timer Started.");

        Instant startTime = Instant.now(); // Store start time

        // ✅ Step 3: Ask for the code again when returning the helmet
        System.out.println("\nAfter use, place the helmet back in the box.");

        while (true) {
            System.out.println("\nEnter the code to return the helmet: ");
            String returnCode = scanner.nextLine();

            if (!returnCode.equals(unlockCode)) {
                System.out.println("Incorrect code. Please try again.");
                continue;
            }

            Instant endTime = Instant.now();
            long durationMinutes = Duration.between(startTime, endTime).toMinutes();
            if (durationMinutes == 0) durationMinutes = 1; // Minimum charge for 1 min

            double totalFare = durationMinutes * pricePerMinute;
            System.out.println("Total rental time: " + durationMinutes + " minutes");
            System.out.println("Total fare: ₹" + totalFare);

            if (wallet.getBalance() < totalFare) {
                System.out.println("Insufficient balance to complete the payment.");
                return;
            }

            System.out.println("Code verified! Scanning RFID tag...");
            String scannedRFID = rfidScanner.scanRFID();

            System.out.println("Detecting helmet weight...");
            double detectedWeight = weightSensor.getWeight();

            if (lockManager.lockHelmet(unlockCode, scannedRFID, detectedWeight)) {
                wallet.deductAmount(totalFare); // ✅ Deduct from wallet
                System.out.println("Helmet successfully returned. Locking box...");
                System.out.println("₹" + totalFare + " deducted. Remaining Balance: ₹" + wallet.getBalance());
                break;
            } else {
                System.out.println("Helmet not placed correctly. Please try again.");
            }
        }

        System.out.println("Helmet rental session complete. Thank you!");
        scanner.close();
    }
}
