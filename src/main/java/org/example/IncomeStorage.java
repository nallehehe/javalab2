package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.example.BudgetTracker.scanner;

public class IncomeStorage {
    private Map<Integer, Income> incomeList = new HashMap<>();
    private String fileName = "src/main/income.json";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public IncomeStorage() {
    }

    public void readFile() throws IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();
        } else {
            Type type = new TypeToken<Map<Integer, Income>>() {}.getType();

            Reader reader = new FileReader(file);

            incomeList = gson.fromJson(reader, type);
        }
    }

    public void saveFile() throws IOException {
        FileWriter fw = new FileWriter(new File(fileName));

        gson.toJson(incomeList, fw);

        fw.close();
    }

    public void createIncome() throws IOException {
        readFile();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = LocalDate.now().format(formatter);

        System.out.println("First name: ");
        String firstName = scanner.nextLine();

        System.out.println("Last name: ");
        String lastName = scanner.nextLine();

        System.out.println("How much did you recieve?");
        double amount = Menu.doubleTryCatch();
        scanner.nextLine();

        System.out.println("All income categories: ");
        for (EIncomeCategory category : EIncomeCategory.values()) {
            System.out.println(category.getIncomeCategoryOption() + ". " + category.name());
        }

        System.out.println("What category?");
        int category = Menu.categoryTryCatch(EIncomeCategory.values().length);

        EIncomeCategory selectedIncomeCategory = EIncomeCategory.values()[category - 1];

        User user = new User(firstName, lastName);

        int maxId = incomeList.isEmpty() ? 0 : Collections.max(incomeList.keySet());
        int newId = maxId + 1;

        Income income = new Income(newId, amount, date, user, selectedIncomeCategory);

        addIncome(income);

        System.out.println("Income with the id " + income.getId() + " has been created.");
    }

    public void updateIncome() throws IOException {
        readFile();

        System.out.println("What income would you like to update?");
        int id = Menu.intTryCatch();
        scanner.nextLine();

        if (!incomeList.containsKey(id)) {
            System.out.println("An income with that id does not exist.");
        } else {
            System.out.println("First name: ");
            String firstName = scanner.nextLine();

            System.out.println("Last name: ");
            String lastName = scanner.nextLine();

            System.out.println("How much did you recieve?");
            double amount = Menu.doubleTryCatch();
            scanner.nextLine();

            System.out.println("All income categories: ");
            for (EIncomeCategory category : EIncomeCategory.values()) {
                System.out.println(category.getIncomeCategoryOption() + ". " + category.name());
            }

            System.out.println("What category?");
            int category = Menu.categoryTryCatch(EIncomeCategory.values().length);
            EIncomeCategory selectedIncomeCategory = EIncomeCategory.values()[category - 1];

            User user = new User(firstName, lastName);

            String date = incomeList.get(id).getDate();

            Income income = new Income(id, amount, date, user, selectedIncomeCategory);

            addIncome(income);

            System.out.println("Income with the id " + income.getId() + " has been updated.");
        }
    }

    public void deleteIncome() throws IOException {
        readFile();
        System.out.println("What income would you like to delete?");
        int id = Menu.intTryCatch();
        scanner.nextLine();
        if (!incomeList.containsKey(id)) {
            System.out.println("An income with that id does not exist.");
        } else {
            incomeList.remove(id);

            System.out.println("An income with the id " + id + " has been deleted.");

            saveFile();
        }
    }

    public void allIncomes() throws IOException {
        readFile();

        for (int id : incomeList.keySet()) {
            Income income = incomeList.get(id);
            System.out.println("\n----------------------");
            System.out.println("Id: " + id);
            System.out.println("First name: " + income.getUser().getFirstName());
            System.out.println("Last name: " + income.getUser().getLastName());
            System.out.println("Amount: " + income.getAmount());
            System.out.println("Date: " + income.getDate());
            System.out.println("Category: " + income.getIncomeCategory());
        }
    }

    public void addIncome(Income income) throws IOException {
        incomeList.put(income.getId(), income);
        saveFile();
    }
}
