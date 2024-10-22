package org.example;

public class Expense extends Transaction{
    private EExpenseCategory expenseCategory;

    public Expense(int id, double amount, String date, User user, EExpenseCategory expenseCategory) {
        super(id, amount, date, user);
        this.expenseCategory = expenseCategory;
    }

    public EExpenseCategory getExpenseCategory() {
        return this.expenseCategory;
    }
}
