package org.example;

import java.time.LocalDate;

public class Expense extends Transaction{
    private EExpenseCategory eExpenseCategory;

    public Expense(double amount, LocalDate date, User user, EExpenseCategory eExpenseCategory) {
        super(amount, date, user);
        this.eExpenseCategory = eExpenseCategory;
    }
}
