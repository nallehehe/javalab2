package org.example;

import com.google.gson.Gson;

import java.util.Map;

public class ExpenseStorage {
    private Map<String, Expense> expenseList;
    private String fileName = "src/main/expense.json";
    Gson gson = new Gson();
}
