package org.example;

import java.time.LocalDate;

public class Transaction {
    private double amount;
    private LocalDate date;
    private User user;

    public Transaction(double amount, LocalDate date, User user) {
        this.amount = amount;
        this.date = date;
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }
}
