package org.example;

public class Transaction {
    private double amount;
    private String date;
    private User user;

    public Transaction(double amount, String date, User user) {
        this.amount = amount;
        this.date = date;
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }
}
