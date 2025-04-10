package com.asn.project;

public class WeightSensor {
    private static final double MIN_HELMET_WEIGHT = 1.0; // Minimum weight threshold (kg)

    public double getWeight() {
        System.out.println("Detecting weight...");

        try {
            Thread.sleep(2000); // Simulate weight detection delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double detectedWeight = 1.5; // Simulated helmet weight
        System.out.println("Detected Weight: " + detectedWeight + " kg");
        return detectedWeight;
    }

    public boolean isHelmetPlaced(double detectedWeight) {
        return detectedWeight >= MIN_HELMET_WEIGHT;
    }
}
