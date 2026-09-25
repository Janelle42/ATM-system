package com.example.ATM_system;

import java.time.LocalDateTime;

public class Transaction {

    private String type;
    private double amount;
    private double balance;
    private LocalDateTime dateTime;

    public Transaction(String type, double amount, double balance) {
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.dateTime = LocalDateTime.now();
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}