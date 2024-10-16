package org.example;

public class Expense extends Transaction{
    private EExpenseCategory expenseCategory;

    public Expense(double amount, String date, User user, EExpenseCategory eExpenseCategory) {
        super(amount, date, user);
        this.expenseCategory = eExpenseCategory;
    }
}
