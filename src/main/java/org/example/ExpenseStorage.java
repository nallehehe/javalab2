package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ExpenseStorage {
    private Map<Integer, Expense> expenseList = new HashMap<>();
    private String fileName = "src/main/expense.json";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ExpenseStorage() {
    }

    public void readFile() throws IOException {
        Type type = new TypeToken<Map<Integer, Expense>>() {}.getType();

        Reader reader = new FileReader(fileName);

        expenseList = gson.fromJson(reader, type);
    }

    /*instansierar readFile metoden här för att kolla om json-filen redan har existerande data och om id:t är unikt så sparas ett nytt json objekt ner
    * om id:t inte är unikt så skrivs det över!
    *
    * skapade även ett if-statement som kollar om expense.json ens existerar och om den inte gör det så skapar den en som du då kan spara data i*/
    public void saveFile(Expense expense) throws IOException {
        if (fileName.isEmpty()) {
            File file = new File(fileName);
        } else {
            expenseList.put(expense.getId(), expense);

            FileWriter fw = new FileWriter(new File(fileName));

            gson.toJson(expenseList, fw);

            fw.close();

            System.out.println("Expense saved to " + fileName);
        }
    }


    public Map<Integer, Expense> getExpenseList() {
        return expenseList;
    }

    public void addExpense(Expense expense) throws IOException {
        expenseList.put(expense.getId(), expense);
        saveFile(expense);
    }
}
