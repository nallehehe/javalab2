package org.example;

import java.time.LocalDate;

public class Income extends Transaction {
    public EIncomeCategory eIncomeCategory;

    public Income(double amount, LocalDate date, User user) {
        super(amount, date, user);
    }
}
