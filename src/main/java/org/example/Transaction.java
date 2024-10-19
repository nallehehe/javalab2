package org.example;

public class Transaction {
    //private static AtomicInteger counter = new AtomicInteger(0);
    private int id;
    private double amount;
    private String date;
    private User user;

    public Transaction(int id, double amount, String date, User user) {
        this.id = id;
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

    public int getId() {
        return id;
    }
}
