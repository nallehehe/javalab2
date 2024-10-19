package org.example;

public class Income extends Transaction {
    public EIncomeCategory eIncomeCategory;

    public Income(int id, double amount, String date, User user, EIncomeCategory eIncomeCategory) {
        super(id, amount, date, user);
        this.eIncomeCategory = eIncomeCategory;
    }
}
