package org.example;

public class Income extends Transaction {
    public EIncomeCategory eIncomeCategory;

    public Income(double amount, String date, User user) {
        super(amount, date, user);
    }
}
