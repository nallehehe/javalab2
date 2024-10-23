package org.example;

public class Income extends Transaction {
    public EIncomeCategory incomeCategory;

    public Income(int id, double amount, String date, User user, EIncomeCategory incomeCategory) {
        super(id, amount, date, user);
        this.incomeCategory = incomeCategory;
    }

    public EIncomeCategory getIncomeCategory() {
        return this.incomeCategory;
    }
}
