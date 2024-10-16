package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ExpenseStorage {
    private Map<String, Expense> expenseList = new HashMap<>();
    private String fileName = "src/main/expense.json";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void readFile() throws IOException {
        Type type = new TypeToken<Map<String, Expense>>() {}.getType();

        Reader reader = new FileReader(fileName);

        expenseList = gson.fromJson(reader, type);

    }

    public void saveFile(Expense expense) throws IOException {
        expenseList.put(expense.getUser().getId(), expense);

        FileWriter fw = new FileWriter(new File(fileName));

        gson.toJson(expenseList, fw);

        fw.close();

        System.out.println("Expense saved to " + fileName);
    }
}
