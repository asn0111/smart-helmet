package com.asn.project;
public class Wallet {
    private double balance;

    public Wallet(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deductAmount(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Amount ₹" + amount + " deducted. New Balance: ₹" + balance);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void addFunds(double amount) {
        balance += amount;
        System.out.println("₹" + amount + " added. New Balance: ₹" + balance);
    }
}
